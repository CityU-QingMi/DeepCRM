    @Test
    public void testCloseFlushDoesNotBlock() throws Exception
    {
        final DeferredContentProvider provider = new DeferredContentProvider();

        provider.close();

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
        Assert.assertTrue(await(task, 5, TimeUnit.SECONDS));
    }
