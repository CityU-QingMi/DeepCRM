  @Override
  public Collection<String> pauseJobs(GroupMatcher<JobKey> matcher) throws JobPersistenceException {
    Collection<String> pausedGroups = new HashSet<String>();
    lock();
    try {

      Set<JobKey> jobKeys = getJobKeys(matcher);

      for (JobKey jobKey : jobKeys) {
        for (OperableTrigger trigger : getTriggersForJob(jobKey)) {
          pauseTrigger(trigger.getKey());
        }
        pausedGroups.add(jobKey.getGroup());
      }
      // make sure to account for an exact group match for a group that doesn't yet exist
      StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
      if (operator.equals(StringOperatorName.EQUALS)) {
        jobFacade.addPausedGroup(matcher.getCompareToValue());
        pausedGroups.add(matcher.getCompareToValue());
      }
    } finally {
      unlock();
    }
    return pausedGroups;
  }
