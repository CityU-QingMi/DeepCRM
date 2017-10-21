  @Override
  public Collection<String> resumeTriggers(GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
    Collection<String> groups = new HashSet<String>();
    lock();
    try {
      Set<TriggerKey> triggerKeys = getTriggerKeys(matcher);

      for (TriggerKey triggerKey : triggerKeys) {
        TriggerWrapper tw = triggerFacade.get(triggerKey);
        if (tw != null) {
          String jobGroup = tw.getJobKey().getGroup();

          if (jobFacade.pausedGroupsContain(jobGroup)) {
            continue;
          }
          groups.add(triggerKey.getGroup());
        }
        resumeTrigger(triggerKey);
      }
      triggerFacade.removeAllPausedGroups(groups);
    } finally {
      unlock();
    }
    return groups;
  }
