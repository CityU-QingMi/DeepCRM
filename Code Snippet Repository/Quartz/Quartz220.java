    public SchedulerMetaData getMetaData() throws SchedulerException {
        try {
            RemotableQuartzScheduler sched = getRemoteScheduler();
            return new SchedulerMetaData(getSchedulerName(),
                    getSchedulerInstanceId(), getClass(), true, isStarted(), 
                    isInStandbyMode(), isShutdown(), sched.runningSince(), 
                    sched.numJobsExecuted(), sched.getJobStoreClass(), 
                    sched.supportsPersistence(), sched.isClustered(), sched.getThreadPoolClass(), 
                    sched.getThreadPoolSize(), sched.getVersion());

        } catch (RemoteException re) {
            throw invalidateHandleCreateException(
                    "Error communicating with remote scheduler.", re);
        }

    }
