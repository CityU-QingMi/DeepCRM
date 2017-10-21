    private void testResourceSweepRemove(final boolean sweep) throws Exception
    {
        long period = 1000;
        final CountDownLatch taskLatch = new CountDownLatch(1);
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

        final CountDownLatch sweepLatch = new CountDownLatch(1);
        sweeper.offer(new Sweeper.Sweepable()
        {
            @Override
            public boolean sweep()
            {
                sweepLatch.countDown();
                return sweep;
            }
        });

        Assert.assertTrue(sweepLatch.await(2 * period, TimeUnit.MILLISECONDS));
        Assert.assertTrue(taskLatch.await(2 * period, TimeUnit.MILLISECONDS));
        Assert.assertEquals(sweep ? 0 : 1, sweeper.getSize());

        sweeper.stop();
    }
