    @Test
    public void testFullMethod() throws Exception
    {
        configureServer(new HelloWorldHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort());
             StacklessLogging stackless = new StacklessLogging(HttpConnection.class))
        {
            client.setSoTimeout(10000);
            ((AbstractLogger) Log.getLogger(HttpConnection.class)).info("expect request is too large, then ISE extra data ...");
            OutputStream os = client.getOutputStream();

            byte[] buffer = new byte[64 * 1024];
            Arrays.fill(buffer, (byte)'A');

            os.write(buffer);
            os.flush();

            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response, Matchers.containsString("HTTP/1.1 431 "));
        }
    }
