    private void waitForIdle(QueuedThreadPool tp, int idle)
    {
        long now=System.currentTimeMillis();
        long start=now;
        while (tp.getIdleThreads()!=idle && (now-start)<10000)
        {
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {}
            now=System.currentTimeMillis();
        }
        Assert.assertEquals(idle, tp.getIdleThreads());
    }
