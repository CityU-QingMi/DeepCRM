    @Test
    @Slow
    public void testRequest2Sliced2() throws Exception
    {
        configureServer(new EchoHandler());

        byte[] bytes = REQUEST2.getBytes();
        int splits = bytes.length-REQUEST2_CONTENT.length()+5;
        for (int i = 0; i < splits; i += 1)
        {
            int[] points = new int[]{i};
            StringBuilder message = new StringBuilder();

            message.append("iteration #").append(i + 1);

            try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
            {
                OutputStream os = client.getOutputStream();

                writeFragments(bytes, points, message, os);

                // Read the response
                String response = readResponse(client);

                // Check the response
                assertEquals("response for " + i + " " + message.toString(), RESPONSE2, response);
                
                Thread.sleep(10);
            }
        }
    }
