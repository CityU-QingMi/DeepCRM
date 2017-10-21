    @Test
    public void testBatchModeOn() throws Exception
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
            remote.setBatchingAllowed(true);

            Future<Void> future = remote.sendText("batch_mode_on");
            // The write is aggregated and therefore completes immediately.
            future.get(1, TimeUnit.MICROSECONDS);

            // Did not flush explicitly, so the message should not be back yet.
            Assert.assertFalse(latch.await(1, TimeUnit.SECONDS));

            // Explicitly flush.
            remote.flushBatch();

            // Wait for the echo.
            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        }
    }
