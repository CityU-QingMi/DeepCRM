    @Test
    public void testBlockingTimeoutLargerThanIdleTimeoutBlockingReadIdleTimeoutFires() throws Exception
    {
        long idleTimeout = 2500;
        long blockingTimeout = 3 * idleTimeout;
        httpConfig.setBlockingTimeout(blockingTimeout);
        CountDownLatch handlerLatch = new CountDownLatch(1);
        start(new BlockingReadHandler(handlerLatch));
        setServerIdleTimeout(idleTimeout);

        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            DeferredContentProvider contentProvider = new DeferredContentProvider(ByteBuffer.allocate(1));
            CountDownLatch resultLatch = new CountDownLatch(1);
            client.POST(newURI())
                    .content(contentProvider)
                    .send(result ->
                    {
                        if (result.getResponse().getStatus() == HttpStatus.INTERNAL_SERVER_ERROR_500)
                            resultLatch.countDown();
                    });

            // Blocking read should timeout.
            Assert.assertTrue(handlerLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
            // Complete the request.
            contentProvider.close();
            Assert.assertTrue(resultLatch.await(5, TimeUnit.SECONDS));
        }
    }
