  @Override
  public boolean removeJob(JobKey jobKey) throws JobPersistenceException {
    boolean found = false;
    lock();
    try {
      List<OperableTrigger> trigger = getTriggersForJob(jobKey);
      for (OperableTrigger trig : trigger) {
        this.removeTrigger(trig.getKey());
        found = true;
      }

      found = (jobFacade.remove(jobKey) != null) | found;
      if (found) {
        Set<String> grpSet = toolkitDSHolder.getOrCreateJobsGroupMap(jobKey.getGroup());
        grpSet.remove(jobKey.getName());
        if (grpSet.isEmpty()) {
          toolkitDSHolder.removeJobsGroupMap(jobKey.getGroup());
          jobFacade.removeGroup(jobKey.getGroup());
        }
      }
    } finally {
      unlock();
    }

    return found;
  }
