    @Test
    public void testUploadWithDeferredContentProviderFailsMultipleOffers() throws Exception
    {
        start(new EmptyServerHandler());

        final CountDownLatch failLatch = new CountDownLatch(2);
        final Callback callback = new Callback()
        {
            @Override
            public void failed(Throwable x)
            {
                failLatch.countDown();
            }
        };

        final CountDownLatch completeLatch = new CountDownLatch(1);
        final DeferredContentProvider content = new DeferredContentProvider();
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .content(content)
                .onRequestBegin(request ->
                {
                    content.offer(ByteBuffer.wrap(new byte[256]), callback);
                    content.offer(ByteBuffer.wrap(new byte[256]), callback);
                    request.abort(new Exception("explicitly_thrown_by_test"));
                })
                .send(result ->
                {
                    if (result.isFailed())
                        completeLatch.countDown();
                });
        Assert.assertTrue(completeLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(failLatch.await(5, TimeUnit.SECONDS));

        // Make sure that adding more content results in the callback to be failed.
        final CountDownLatch latch = new CountDownLatch(1);
        content.offer(ByteBuffer.wrap(new byte[128]), new Callback()
        {
            @Override
            public void failed(Throwable x)
            {
                latch.countDown();
            }
        });
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
