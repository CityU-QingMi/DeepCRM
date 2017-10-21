  @Override
  public Set<TriggerKey> getTriggerKeys(GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
    lock();
    try {
      Set<String> groupNames = new HashSet<String>();
      switch (matcher.getCompareWithOperator()) {
        case EQUALS:
          groupNames.add(matcher.getCompareToValue());
          break;
        default:
          for (String group : triggerFacade.allTriggersGroupNames()) {
            if (matcher.getCompareWithOperator().evaluate(group, matcher.getCompareToValue())) {
              groupNames.add(group);
            }
          }
      }

      Set<TriggerKey> out = new HashSet<TriggerKey>();

      for (String groupName : groupNames) {
        Set<String> grpSet = toolkitDSHolder.getOrCreateTriggersGroupMap(groupName);

        for (String key : grpSet) {
          TriggerKey triggerKey = new TriggerKey(key, groupName);
          TriggerWrapper tw = triggerFacade.get(triggerKey);
          if (tw != null) {
            out.add(triggerKey);
          }
        }
      }

      return out;
    } finally {
      unlock();
    }
  }
