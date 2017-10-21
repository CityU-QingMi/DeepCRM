    public void createScheduler(String schedulerName,
            String schedulerInstanceId, ThreadPool threadPool,
            JobStore jobStore, String rmiRegistryHost, int rmiRegistryPort,
            long idleWaitTime, long dbFailureRetryInterval)
        throws SchedulerException {
        createScheduler(schedulerName,
                schedulerInstanceId, threadPool,
                jobStore, null, // plugins
                rmiRegistryHost, rmiRegistryPort,
                idleWaitTime, dbFailureRetryInterval,
                DEFAULT_JMX_EXPORT, DEFAULT_JMX_OBJECTNAME);
    }
