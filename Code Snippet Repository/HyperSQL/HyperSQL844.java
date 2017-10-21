    protected static ExecutorService getExecutorService() {

        if (JDBCSQLXML.executorService == null) {
            int      corePoolSize    = 1;
            int      maximumPoolSize = 10;
            long     keepAliveTime   = 1;
            TimeUnit unit            = TimeUnit.SECONDS;

            JDBCSQLXML.workQueue = new ArrayBlockingQueue<Runnable>(10);
            JDBCSQLXML.executorService = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        return executorService;
    }
