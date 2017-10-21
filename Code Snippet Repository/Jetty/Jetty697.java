    @Test
    public void testWhenEmptyFlushDoesNotBlock() throws Exception
    {
        final DeferredContentProvider provider = new DeferredContentProvider();

        Future<?> task = executor.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                provider.flush();
                return null;
            }
        });

        Assert.assertTrue(await(task, 5, TimeUnit.SECONDS));
    }
