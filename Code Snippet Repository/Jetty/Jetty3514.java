    @Test
    public void testRequest1() throws Exception
    {
        configureServer(new HelloWorldHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            os.write(REQUEST1.getBytes());
            os.flush();

            // Read the response.
            String response = readResponse(client);

            // Check the response
            assertEquals("response", RESPONSE1, response);
        }
    }
