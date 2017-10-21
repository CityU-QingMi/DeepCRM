    @Test
    public void testBlockingTimeoutLargerThanIdleTimeoutBlockingWriteIdleTimeoutFires() throws Exception
    {
        long idleTimeout = 2500;
        long blockingTimeout = 3 * idleTimeout;
        httpConfig.setBlockingTimeout(blockingTimeout);
        CountDownLatch handlerLatch = new CountDownLatch(1);
        start(new BlockingWriteHandler(handlerLatch));
        setServerIdleTimeout(idleTimeout);

        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            BlockingQueue<Callback> callbacks = new LinkedBlockingQueue<>();
            CountDownLatch resultLatch = new CountDownLatch(1);
            client.newRequest(newURI())
                    .onResponseContentAsync((response, content, callback) ->
                    {
                        // Do not succeed the callback so the server will block writing.
                        callbacks.offer(callback);
                    })
                    .send(result ->
                    {
                        if (result.isFailed())
                            resultLatch.countDown();
                    });

            // Blocking read should timeout.
            Assert.assertTrue(handlerLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
            // After the server stopped sending, consume on the client to read the early EOF.
            while (true)
            {
                Callback callback = callbacks.poll(1, TimeUnit.SECONDS);
                if (callback == null)
                    break;
                callback.succeeded();
            }
            Assert.assertTrue(resultLatch.await(5, TimeUnit.SECONDS));
        }
    }
