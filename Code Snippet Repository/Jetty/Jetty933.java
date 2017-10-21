    @Override
    public boolean isLowOnThreads()
    {
        final Executor executor=_executor;
        if (executor instanceof ThreadPool)
            return ((ThreadPool)executor).isLowOnThreads();
        
        if (executor instanceof ThreadPoolExecutor)
        {
            final ThreadPoolExecutor tpe = (ThreadPoolExecutor)executor;
            // getActiveCount() locks the thread pool, so execute it last
            return tpe.getPoolSize() == tpe.getMaximumPoolSize() &&
                    tpe.getQueue().size() >= tpe.getPoolSize() - tpe.getActiveCount();
        }
        return false;
    }
