    @Override
    public int getIdleThreads()
    {
        final Executor executor=_executor;
        if (executor instanceof ThreadPool)
            return ((ThreadPool)executor).getIdleThreads();
        
        if (executor instanceof ThreadPoolExecutor)
        {
            final ThreadPoolExecutor tpe = (ThreadPoolExecutor)executor;
            return tpe.getPoolSize() - tpe.getActiveCount();
        }
        return -1;
    }
