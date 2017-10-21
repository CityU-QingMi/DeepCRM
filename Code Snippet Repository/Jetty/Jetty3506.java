    @Test
    public void testDualRequest1() throws Exception
    {
        configureServer(new HelloWorldHandler());

        try (Socket client1 = newSocket(_serverURI.getHost(), _serverURI.getPort()); Socket client2 = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os1 = client1.getOutputStream();
            OutputStream os2 = client2.getOutputStream();

            os1.write(REQUEST1.getBytes());
            os2.write(REQUEST1.getBytes());
            os1.flush();
            os2.flush();

            // Read the response.
            String response1 = readResponse(client1);
            String response2 = readResponse(client2);

            // Check the response
            assertEquals("client1", RESPONSE1, response1);
            assertEquals("client2", RESPONSE1, response2);
        }
    }
