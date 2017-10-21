  @Override
  public Collection<String> resumeJobs(GroupMatcher<JobKey> matcher) throws JobPersistenceException {
    Collection<String> groups = new HashSet<String>();
    lock();
    try {
      Set<JobKey> jobKeys = getJobKeys(matcher);

      for (JobKey jobKey : jobKeys) {
        if (groups.add(jobKey.getGroup())) {
          jobFacade.removePausedJobGroup(jobKey.getGroup());
        }
        for (OperableTrigger trigger : getTriggersForJob(jobKey)) {
          resumeTrigger(trigger.getKey());
        }
      }
    } finally {
      unlock();
    }
    return groups;
  }
