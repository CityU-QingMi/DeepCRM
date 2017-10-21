    private static int defaultSelectors(Executor executor)
    {
        if (executor instanceof ThreadPool.SizedThreadPool)
        {
            int threads = ((ThreadPool.SizedThreadPool)executor).getMaxThreads();
            int cpus = Runtime.getRuntime().availableProcessors();
            return Math.max(1,Math.min(cpus/2,threads/16));
        }
        
        return Math.max(1,Runtime.getRuntime().availableProcessors()/2);
    }
