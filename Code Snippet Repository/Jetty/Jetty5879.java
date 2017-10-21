    @Test
    public void idleTest() throws Exception
    {
        AtomicInteger count = new AtomicInteger(0);
        Producer producer = new TestProducer()
        {
            @Override
            public Runnable produce()
            {
                count.incrementAndGet();
                return null;
            }
        };
        
        newExecutionStrategy(producer,_threads);
        _strategy.produce();
        assertThat(count.get(),greaterThan(0));
    }
