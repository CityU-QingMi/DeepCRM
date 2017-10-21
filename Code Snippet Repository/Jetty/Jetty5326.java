    @Override
    public Task schedule(Runnable task, long delay, TimeUnit unit)
    {
        ScheduledThreadPoolExecutor s = scheduler;
        if (s==null)
            return new Task(){
                @Override
                public boolean cancel()
                {
                    return false;
                }};

        ScheduledFuture<?> result = s.schedule(task, delay, unit);
        return new ScheduledFutureTask(result);
    }
