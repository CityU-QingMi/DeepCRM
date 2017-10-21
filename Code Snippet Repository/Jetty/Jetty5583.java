    private static void await(CyclicBarrier barrier)
    {
        try
        {
            barrier.await();
        }
        catch (Exception x)
        {
            throw new RuntimeException(x);
        }
    }
