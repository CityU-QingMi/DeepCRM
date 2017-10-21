  @Override
  public void storeJobsAndTriggers(Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace)
      throws ObjectAlreadyExistsException, JobPersistenceException {

    lock();
    try {
      // make sure there are no collisions...
      if (!replace) {
        for (JobDetail job : triggersAndJobs.keySet()) {
          if (checkExists(job.getKey())) throw new ObjectAlreadyExistsException(job);
          for (Trigger trigger : triggersAndJobs.get(job)) {
            if (checkExists(trigger.getKey())) throw new ObjectAlreadyExistsException(trigger);
          }
        }
      }
      // do bulk add...
      for (JobDetail job : triggersAndJobs.keySet()) {
        storeJob(job, true);
        for (Trigger trigger : triggersAndJobs.get(job)) {
          storeTrigger((OperableTrigger) trigger, true);
        }
      }
    } finally {
      unlock();
    }
  }
