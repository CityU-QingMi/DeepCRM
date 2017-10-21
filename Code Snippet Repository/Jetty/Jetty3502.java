    @Test
    public void testBlockedClient() throws Exception
    {
        configureServer(new HelloWorldHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            // Send a request with chunked input and expect 100
            os.write((
                    "GET / HTTP/1.1\r\n" +
                            "Host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "Transfer-Encoding: chunked\r\n" +
                            "Expect: 100-continue\r\n" +
                            "Connection: Keep-Alive\r\n" +
                            "\r\n"
            ).getBytes());

            // Never send a body.
            // HelloWorldHandler does not read content, so 100 is not sent.
            // So close will have to happen anyway, without reset!

            os.flush();

            client.setSoTimeout(2000);
            long start = System.currentTimeMillis();
            String in = IO.toString(is);
            assertTrue(System.currentTimeMillis() - start < 1000);
            assertTrue(in.indexOf("Connection: close") > 0);
            assertTrue(in.indexOf("Hello world") > 0);
        }
    }
