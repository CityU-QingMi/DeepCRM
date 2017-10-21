  @Override
  public void storeJobAndTrigger(JobDetail newJob, OperableTrigger newTrigger) throws JobPersistenceException {
    lock();
    try {
      storeJob(newJob, false);
      storeTrigger(newTrigger, false);
    } finally {
      unlock();
    }
  }
