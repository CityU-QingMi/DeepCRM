    @Test
    public void testBatchModeAuto() throws Exception
    {
        URI uri = URI.create("ws://localhost:" + connector.getLocalPort());

        final CountDownLatch latch = new CountDownLatch(1);
        WebSocketAdapter adapter = new WebSocketAdapter()
        {
            @Override
            public void onWebSocketText(String message)
            {
                latch.countDown();
            }
        };
        try (Session session = client.connect(adapter, uri).get())
        {
            RemoteEndpoint remote = session.getRemote();

            Future<Void> future = remote.sendStringByFuture("batch_mode_on");
            // The write is aggregated and therefore completes immediately.
            future.get(1, TimeUnit.MICROSECONDS);

            // Wait for the echo.
            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        }
    }
