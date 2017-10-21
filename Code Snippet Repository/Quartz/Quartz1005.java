  @Override
  public void initialize(ClassLoadHelper loadHelper, SchedulerSignaler signaler) throws SchedulerConfigException {
    init();
    realJobStore.setInstanceId(schedInstId);
    realJobStore.setInstanceName(schedName);
    realJobStore.setTcRetryInterval(tcRetryInterval);

    if (misFireThreshold != null) {
      realJobStore.setMisfireThreshold(misFireThreshold);
    }

    if (synchWrite != null) {
      realJobStore.setSynchronousWrite(synchWrite);
    }

    if (estimatedTimeToReleaseAndAcquireTrigger != null) {
      realJobStore.setEstimatedTimeToReleaseAndAcquireTrigger(estimatedTimeToReleaseAndAcquireTrigger);
    }

    realJobStore.initialize(loadHelper, signaler);
  }
