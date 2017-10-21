    protected List<WorkerThread> createWorkerThreads(int createCount) {
        workers = new LinkedList<WorkerThread>();
        for (int i = 1; i<= createCount; ++i) {
            String threadPrefix = getThreadNamePrefix();
            if (threadPrefix == null) {
                threadPrefix = schedulerInstanceName + "_Worker";
            }
            WorkerThread wt = new WorkerThread(this, threadGroup,
                threadPrefix + "-" + i,
                getThreadPriority(),
                isMakeThreadsDaemons());
            if (isThreadsInheritContextClassLoaderOfInitializingThread()) {
                wt.setContextClassLoader(Thread.currentThread()
                        .getContextClassLoader());
            }
            workers.add(wt);
        }

        return workers;
    }
