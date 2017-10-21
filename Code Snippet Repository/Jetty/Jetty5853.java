    @Test
    public void testException() throws Exception
    {
        QueuedThreadPool tp= new QueuedThreadPool();
        tp.setMinThreads(5);
        tp.setMaxThreads(10);
        tp.setIdleTimeout(1000);
        tp.start();
        try (StacklessLogging stackless = new StacklessLogging(QueuedThreadPool.class))
        {
            tp.execute(new Runnable(){ public void run () { throw new IllegalStateException(); } });
            tp.execute(new Runnable(){ public void run () { throw new Error(); } });
            tp.execute(new Runnable(){ public void run () { throw new RuntimeException(); } });
            tp.execute(new Runnable(){ public void run () { throw new ThreadDeath(); } });
            
            Thread.sleep(100);
            assertThat(tp.getThreads(),greaterThanOrEqualTo(5));
        }
    }
