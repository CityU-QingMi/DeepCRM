    private static Runnable take(Queue<Runnable> queue)
    {
        try
        {
            return ((BlockingQueue<Runnable>)queue).take();
        }
        catch (InterruptedException x)
        {
            throw new RuntimeException(x);
        }
    }
