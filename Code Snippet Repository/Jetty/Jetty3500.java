    @Test
    public void testPipeline() throws Exception
    {
        configureServer(new HelloWorldHandler());

        //for (int pipeline=1;pipeline<32;pipeline++)
        for (int pipeline = 1; pipeline < 32; pipeline++)
        {
            try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
            {
                client.setSoTimeout(5000);
                OutputStream os = client.getOutputStream();

                String request = "";

                for (int i = 1; i < pipeline; i++)
                    request +=
                            "GET /data?writes=1&block=16&id=" + i + " HTTP/1.1\r\n" +
                                    "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                                    "user-agent: testharness/1.0 (blah foo/bar)\r\n" +
                                    "accept-encoding: nothing\r\n" +
                                    "cookie: aaa=1234567890\r\n" +
                                    "\r\n";

                request +=
                        "GET /data?writes=1&block=16 HTTP/1.1\r\n" +
                                "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                                "user-agent: testharness/1.0 (blah foo/bar)\r\n" +
                                "accept-encoding: nothing\r\n" +
                                "cookie: aaa=bbbbbb\r\n" +
                                "Connection: close\r\n" +
                                "\r\n";

                os.write(request.getBytes());
                os.flush();

                LineNumberReader in = new LineNumberReader(new InputStreamReader(client.getInputStream()));

                String line = in.readLine();
                int count = 0;
                while (line != null)
                {
                    if ("HTTP/1.1 200 OK".equals(line))
                        count++;
                    line = in.readLine();
                }
                assertEquals(pipeline, count);
            }
        }
    }
