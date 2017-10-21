  @Override
  public void pauseJob(JobKey jobKey) throws JobPersistenceException {
    lock();
    try {
      for (OperableTrigger trigger : getTriggersForJob(jobKey)) {
        pauseTrigger(trigger.getKey());
      }
    } finally {
      unlock();
    }
  }
