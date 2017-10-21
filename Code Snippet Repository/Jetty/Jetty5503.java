    @Test
    public void testResourceAcquiredAndReleased() throws Exception
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

        // Block to make sure "resource" goes out of scope
        {
            Object resource = new Object();
            leakDetector.acquired(resource);
            leakDetector.released(resource);
        }

        gc();

        Assert.assertFalse(latch.await(1, TimeUnit.SECONDS));
    }
