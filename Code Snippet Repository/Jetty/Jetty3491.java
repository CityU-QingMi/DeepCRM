    @Test
    public void testRequest2() throws Exception
    {
        configureServer(new EchoHandler());

        byte[] bytes = REQUEST2.getBytes();
        for (int i = 0; i < LOOPS; i++)
        {
            try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
            {
                OutputStream os = client.getOutputStream();

                os.write(bytes);
                os.flush();

                // Read the response
                String response = readResponse(client);

                // Check the response
                assertEquals("response " + i, RESPONSE2, response);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                _server.dumpStdErr();
                throw e;
            }
        }
    }
