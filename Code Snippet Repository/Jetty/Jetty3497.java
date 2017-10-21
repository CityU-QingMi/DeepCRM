    @Test
    public void testCloseWhileWriteBlocked() throws Exception
    {
        configureServer(new DataHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            os.write((
                    "GET /data?encoding=iso-8859-1&writes=100&block=100000 HTTP/1.1\r\n" +
                            "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "connection: close\r\n" +
                            "content-type: unknown\r\n" +
                            "\r\n"
            ).getBytes());
            os.flush();

            // Read the first part of the response
            byte[] buf = new byte[1024 * 8];
            is.read(buf);
            
            // sleep to ensure server is blocking
            Thread.sleep(500);
            
            // Close the client
            client.close();            
        }

        Thread.sleep(200);
        // check server is still handling requests quickly
        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            client.setSoTimeout(500);
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            os.write(("GET /data?writes=1&block=1024 HTTP/1.1\r\n" +
                            "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "connection: close\r\n" +
                            "content-type: unknown\r\n" +
                            "\r\n"
            ).getBytes());
            os.flush();
  
            String response = IO.toString(is);
            assertThat(response,startsWith("HTTP/1.1 200 OK"));
        }
    }
