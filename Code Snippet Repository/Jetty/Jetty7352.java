    @Test
    public void testInputStreamResponseListenerFailedBeforeResponse() throws Exception
    {
        start(new EmptyServerHandler());
        int port = connector.getLocalPort();
        server.stop();

        InputStreamResponseListener listener = new InputStreamResponseListener();
        // Connect to the wrong port
        client.newRequest("localhost", port)
                .scheme(getScheme())
                .send(listener);
        Result result = listener.await(5, TimeUnit.SECONDS);
        Assert.assertNotNull(result);
    }
