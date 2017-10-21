    @Test
    public void testTrailingContent() throws Exception
    {
        configureServer(new EchoHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            os.write(("GET /R2 HTTP/1.1\015\012" +
                    "Host: localhost\015\012" +
                    "Content-Length: 5\015\012" +
                    "Content-Type: text/plain\015\012" +
                    "Connection: close\015\012" +
                    "\015\012" +
                    "ABCDE\015\012" +
                    "\015\012"
            ).getBytes());
            os.flush();

            // Read the response.
            String response = readResponse(client);
            assertTrue(response.indexOf("200") > 0);
        }
    }
