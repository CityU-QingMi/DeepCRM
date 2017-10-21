    public static int reservedThreads(Executor executor,int capacity)
    {
        if (capacity>=0)
            return capacity;
        int cpus = Runtime.getRuntime().availableProcessors();
        if (executor instanceof ThreadPool.SizedThreadPool)
        {
            int threads = ((ThreadPool.SizedThreadPool)executor).getMaxThreads();
            return Math.max(1, Math.min(cpus, threads / 8));
        }
        return cpus;
    }
