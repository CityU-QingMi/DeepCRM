  @Override
  public void resumeJob(JobKey jobKey) throws JobPersistenceException {

    lock();
    try {
      for (OperableTrigger trigger : getTriggersForJob(jobKey)) {
        resumeTrigger(trigger.getKey());
      }
    } finally {
      unlock();
    }
  }
