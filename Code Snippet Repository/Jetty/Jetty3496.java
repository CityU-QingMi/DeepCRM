    @Test
    public void testBlockingWhileWritingResponseContent() throws Exception
    {
        configureServer(new DataHandler());

        long start = System.currentTimeMillis();
        int total = 0;
        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            os.write((
                    "GET /data?writes=256&block=1024 HTTP/1.1\r\n" +
                            "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "connection: close\r\n" +
                            "content-type: unknown\r\n" +
                            "\r\n"
            ).getBytes());
            os.flush();

            int len = 0;
            byte[] buf = new byte[1024 * 32];
            int sleeps=0;
            while (len >= 0)
            {
                len = is.read(buf);
                if (len > 0)
                {
                    total += len;
                    if ((total/10240)>sleeps)
                    {
                        Thread.sleep(200);
                        sleeps++;
                    }
                }
            }

            assertTrue(total > (256 * 1024));
            assertTrue(30000L > (System.currentTimeMillis() - start));
        }
    }
