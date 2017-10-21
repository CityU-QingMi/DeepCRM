    @Test
    public void testSimple() throws Exception
    {
        configureServer(new HelloWorldHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            os.write("GET / HTTP/1.0\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1));
            os.flush();

            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response, Matchers.containsString("HTTP/1.1 200 OK"));
            Assert.assertThat(response, Matchers.containsString("Hello world"));
        }
    }
