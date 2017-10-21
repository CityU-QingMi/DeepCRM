    @Test
    public void testFragmentedChunk() throws Exception
    {
        configureServer(new EchoHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            os.write(("GET /R2 HTTP/1.1\015\012" +
                    "Host: localhost\015\012" +
                    "Transfer-Encoding: chunked\015\012" +
                    "Content-Type: text/plain\015\012" +
                    "Connection: close\015\012" +
                    "\015\012").getBytes());
            os.flush();
            Thread.sleep(1000);
            os.write(("5").getBytes());
            Thread.sleep(1000);
            os.write(("\015\012").getBytes());
            os.flush();
            Thread.sleep(1000);
            os.write(("ABCDE\015\012" +
                    "0;\015\012\015\012").getBytes());
            os.flush();

            // Read the response.
            String response = readResponse(client);
            assertThat(response,containsString("200"));
        }
    }
