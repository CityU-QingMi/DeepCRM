    @Test
    public void testBatchModeOff() throws Exception
    {
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create().build();

        URI uri = URI.create("ws://localhost:" + connector.getLocalPort());

        final CountDownLatch latch = new CountDownLatch(1);
        EndpointAdapter endpoint = new EndpointAdapter()
        {
            @Override
            public void onMessage(String message)
            {
                latch.countDown();
            }
        };

        try (Session session = client.connectToServer(endpoint, config, uri))
        {
            RemoteEndpoint.Async remote = session.getAsyncRemote();
            remote.setBatchingAllowed(false);

            Future<Void> future = remote.sendText("batch_mode_off");
            // The write is immediate.
            future.get(1, TimeUnit.SECONDS);

            // Wait for the echo.
            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        }
    }
