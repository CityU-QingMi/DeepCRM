    @Test
    public void testFullURI() throws Exception
    {
        configureServer(new HelloWorldHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort());
             StacklessLogging stackless = new StacklessLogging(HttpConnection.class))
        {
            ((AbstractLogger)Log.getLogger(HttpConnection.class)).info("expect URI is too large, then ISE extra data ...");
            OutputStream os = client.getOutputStream();

            byte[] buffer = new byte[64 * 1024];
            buffer[0]='G';
            buffer[1]='E';
            buffer[2]='T';
            buffer[3]=' ';
            buffer[4]='/';
            Arrays.fill(buffer, 5,buffer.length-1,(byte)'A');

            os.write(buffer);
            os.flush();

            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response, Matchers.containsString("HTTP/1.1 414 "));
        }
    }
