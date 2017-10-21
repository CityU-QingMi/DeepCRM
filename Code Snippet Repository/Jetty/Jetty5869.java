    @Test
    public void testSweepThrows() throws Exception
    {
        try(StacklessLogging scope = new StacklessLogging(Sweeper.class))
        {
            long period = 500;
            final CountDownLatch taskLatch = new CountDownLatch(2);
            Sweeper sweeper = new Sweeper(scheduler, period)
            {
                @Override
                public void run()
                {
                    super.run();
                    taskLatch.countDown();
                }
            };
            sweeper.start();

            final CountDownLatch sweepLatch = new CountDownLatch(2);
            sweeper.offer(new Sweeper.Sweepable()
            {
                @Override
                public boolean sweep()
                {
                    sweepLatch.countDown();
                    throw new NullPointerException("Test exception!");
                }
            });

            Assert.assertTrue(sweepLatch.await(4 * period, TimeUnit.MILLISECONDS));
            Assert.assertTrue(taskLatch.await(4 * period, TimeUnit.MILLISECONDS));
            Assert.assertEquals(1, sweeper.getSize());

            sweeper.stop();
        }
    }
