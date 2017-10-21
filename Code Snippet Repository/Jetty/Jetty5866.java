    @Test
    public void testTaskThrowsException() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(TimerScheduler.class))
        {
            long delay = 500;
            _scheduler.schedule(new Runnable()
            {
                @Override
                public void run()
                {
                    throw new RuntimeException("Thrown by testTaskThrowsException");
                }
            }, delay, TimeUnit.MILLISECONDS);

            TimeUnit.MILLISECONDS.sleep(2 * delay);

            // Check whether after a task throwing an exception, the scheduler is still working

            final CountDownLatch latch = new CountDownLatch(1);
            _scheduler.schedule(new Runnable()
            {
                @Override
                public void run()
                {
                    latch.countDown();
                }
            }, delay, TimeUnit.MILLISECONDS);

            Assert.assertTrue(latch.await(2 * delay, TimeUnit.MILLISECONDS));
        }
    }
