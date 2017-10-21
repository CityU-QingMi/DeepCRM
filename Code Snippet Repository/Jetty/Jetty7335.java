    private boolean await(CountDownLatch latch, long time, TimeUnit unit)
    {
        try
        {
            return latch.await(time, unit);
        }
        catch (InterruptedException x)
        {
            throw new RuntimeException(x);
        }
    }
