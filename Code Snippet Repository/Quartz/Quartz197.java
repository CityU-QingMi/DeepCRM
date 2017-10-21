    public void createScheduler(String schedulerName,
            String schedulerInstanceId, ThreadPool threadPool,
            JobStore jobStore, Map<String, SchedulerPlugin> schedulerPluginMap,
            String rmiRegistryHost, int rmiRegistryPort,
            long idleWaitTime, long dbFailureRetryInterval,
            boolean jmxExport, String jmxObjectName)
        throws SchedulerException {
        createScheduler(schedulerName, schedulerInstanceId, threadPool,
                DEFAULT_THREAD_EXECUTOR, jobStore, schedulerPluginMap,
                rmiRegistryHost, rmiRegistryPort, idleWaitTime,
                dbFailureRetryInterval, jmxExport, jmxObjectName);
    }
