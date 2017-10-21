    @Test
    public void simpleTest() throws Exception
    {
        final int TASKS = 3*_threads.getMaxThreads();
        final CountDownLatch latch = new CountDownLatch(TASKS);
        Producer producer = new TestProducer()
        {
            int tasks = TASKS;
            @Override
            public Runnable produce()
            {
                if (tasks-->0)
                {
                    return new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            latch.countDown();
                        }
                    };
                }

                return null;
            }
        };
        
        newExecutionStrategy(producer,_threads);

        for (int p=0; latch.getCount()>0 && p<TASKS; p++)
            _strategy.produce();

        assertTrue(latch.await(10,TimeUnit.SECONDS));
    }
