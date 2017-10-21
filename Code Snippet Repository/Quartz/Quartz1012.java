  @Override
  public void clearAllSchedulingData() throws JobPersistenceException {
    lock();
    try {
      // unschedule jobs (delete triggers)
      List<String> lst = getTriggerGroupNames();
      for (String group : lst) {
        Set<TriggerKey> keys = getTriggerKeys(GroupMatcher.triggerGroupEquals(group));
        for (TriggerKey key : keys) {
          removeTrigger(key);
        }
      }
      // delete jobs
      lst = getJobGroupNames();
      for (String group : lst) {
        Set<JobKey> keys = getJobKeys(GroupMatcher.jobGroupEquals(group));
        for (JobKey key : keys) {
          removeJob(key);
        }
      }
      // delete calendars
      lst = getCalendarNames();
      for (String name : lst) {
        removeCalendar(name);
      }
    } finally {
      unlock();
    }
  }
