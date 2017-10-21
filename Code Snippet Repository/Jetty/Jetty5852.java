    private void waitForThreads(QueuedThreadPool tp, int threads)
    {
        long now=System.currentTimeMillis();
        long start=now;
        while (tp.getThreads()!=threads && (now-start)<10000)
        {
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {}
            now=System.currentTimeMillis();
        }
        assertEquals(threads,tp.getThreads());
    }
