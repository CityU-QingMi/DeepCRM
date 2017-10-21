    @Test
    @Slow
    public void testShrink() throws Exception
    {
        final AtomicInteger sleep = new AtomicInteger(100);
        Runnable job = new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(sleep.get());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        };

        QueuedThreadPool tp= new QueuedThreadPool();
        tp.setMinThreads(2);
        tp.setMaxThreads(10);
        tp.setIdleTimeout(400);
        tp.setThreadsPriority(Thread.NORM_PRIORITY-1);

        tp.start();
        waitForIdle(tp,2);
        waitForThreads(tp,2);

        sleep.set(200);
        tp.execute(job);
        tp.execute(job);
        for (int i=0;i<20;i++)
            tp.execute(job);

        waitForThreads(tp,10);
        waitForIdle(tp,0);

        sleep.set(5);
        for (int i=0;i<500;i++)
        {
            tp.execute(job);
            Thread.sleep(10);
        }
        waitForThreads(tp,2);
        waitForIdle(tp,2);
    }
