    @Test
    public void testResourceAcquiredAndNotReleased() throws Exception
    {
        final CountDownLatch latch = new CountDownLatch(1);
        prepare(new LeakDetector<Object>()
        {
            @Override
            protected void leaked(LeakInfo leakInfo)
            {
                latch.countDown();
            }
        });

        leakDetector.acquired(new Object());

        gc();

        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
