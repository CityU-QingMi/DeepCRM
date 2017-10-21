  @Override
  public synchronized void initialize(ClassLoadHelper loadHelper, SchedulerSignaler signaler)
      throws SchedulerConfigException {
    if (clusteredJobStore != null) { throw new IllegalStateException("already initialized"); }

    clusteredJobStore = createNewJobStoreInstance(schedName, Boolean.valueOf(synchWrite));
    clusteredJobStore.setThreadPoolSize(threadPoolSize);

    // apply deferred misfire threshold if present
    if (misfireThreshold != null) {
      clusteredJobStore.setMisfireThreshold(misfireThreshold);
      misfireThreshold = null;
    }

    if (estimatedTimeToReleaseAndAcquireTrigger != null) {
      clusteredJobStore.setEstimatedTimeToReleaseAndAcquireTrigger(estimatedTimeToReleaseAndAcquireTrigger);
      estimatedTimeToReleaseAndAcquireTrigger = null;
    }
    clusteredJobStore.setInstanceId(schedInstanceId);
    clusteredJobStore.setTcRetryInterval(tcRetryInterval);
    clusteredJobStore.initialize(loadHelper, signaler);

  }
