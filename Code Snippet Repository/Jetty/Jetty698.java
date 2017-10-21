    @Test
    public void testOfferFlushBlocksUntilSucceeded() throws Exception
    {
        final DeferredContentProvider provider = new DeferredContentProvider();
        Iterator<ByteBuffer> iterator = provider.iterator();

        provider.offer(ByteBuffer.allocate(0));

        Future<?> task = executor.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                provider.flush();
                return null;
            }
        });

        // Wait until flush() blocks.
        Assert.assertFalse(await(task, 1, TimeUnit.SECONDS));

        // Consume the content and succeed the callback.
        iterator.next();
        ((Callback)iterator).succeeded();

        // Flush should return.
        Assert.assertTrue(await(task, 5, TimeUnit.SECONDS));
    }
