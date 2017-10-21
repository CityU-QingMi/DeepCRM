  @Override
  public Collection<String> pauseTriggers(GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
    HashSet<String> pausedGroups = new HashSet<String>();
    lock();
    try {
      Set<TriggerKey> triggerKeys = getTriggerKeys(matcher);
      for (TriggerKey key : triggerKeys) {
        triggerFacade.addPausedGroup(key.getGroup());
        pausedGroups.add(key.getGroup());
        pauseTrigger(key);
      }
      // make sure to account for an exact group match for a group that doesn't yet exist
      StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
      if (operator.equals(StringOperatorName.EQUALS)) {
        triggerFacade.addPausedGroup(matcher.getCompareToValue());
        pausedGroups.add(matcher.getCompareToValue());
      }
    } finally {
      unlock();
    }
    return pausedGroups;
  }
