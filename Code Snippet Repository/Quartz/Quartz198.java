    public void createScheduler(String schedulerName,
            String schedulerInstanceId, ThreadPool threadPool,
            ThreadExecutor threadExecutor,
            JobStore jobStore, Map<String, SchedulerPlugin> schedulerPluginMap,
            String rmiRegistryHost, int rmiRegistryPort,
            long idleWaitTime, long dbFailureRetryInterval,
            boolean jmxExport, String jmxObjectName)
        throws SchedulerException {
        createScheduler(schedulerName, schedulerInstanceId, threadPool,
                DEFAULT_THREAD_EXECUTOR, jobStore, schedulerPluginMap,
                rmiRegistryHost, rmiRegistryPort, idleWaitTime,
                dbFailureRetryInterval, jmxExport, jmxObjectName, DEFAULT_BATCH_MAX_SIZE, DEFAULT_BATCH_TIME_WINDOW);
    }
