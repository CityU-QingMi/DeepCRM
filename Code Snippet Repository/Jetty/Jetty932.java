    @Override
    public int getThreads()
    {
        final Executor executor=_executor;
        if (executor instanceof ThreadPool)
            return ((ThreadPool)executor).getThreads();
        
        if (executor instanceof ThreadPoolExecutor)
        {
            final ThreadPoolExecutor tpe = (ThreadPoolExecutor)executor;
            return tpe.getPoolSize();
        }
        return -1;
    }
