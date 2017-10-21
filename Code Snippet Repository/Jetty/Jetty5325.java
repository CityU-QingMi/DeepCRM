    @Override
    protected void doStart() throws Exception
    {
        scheduler = new ScheduledThreadPoolExecutor(1, new ThreadFactory()
        {
            @Override
            public Thread newThread(Runnable r)
            {
                Thread thread = ScheduledExecutorScheduler.this.thread = new Thread(threadGroup, r, name);
                thread.setDaemon(daemon);
                thread.setContextClassLoader(classloader);
                return thread;
            }
        });
        scheduler.setRemoveOnCancelPolicy(true);
        super.doStart();
    }
