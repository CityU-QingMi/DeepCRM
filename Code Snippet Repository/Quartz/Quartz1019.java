  @Override
  public Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher) throws JobPersistenceException {
    lock();
    try {
      Set<String> matchingGroups = new HashSet<String>();
      switch (matcher.getCompareWithOperator()) {
        case EQUALS:
          matchingGroups.add(matcher.getCompareToValue());
          break;
        default:
          for (String group : jobFacade.getAllGroupNames()) {
            if (matcher.getCompareWithOperator().evaluate(group, matcher.getCompareToValue())) {
              matchingGroups.add(group);
            }
          }
      }

      Set<JobKey> out = new HashSet<JobKey>();
      for (String matchingGroup : matchingGroups) {

        Set<String> grpJobNames = toolkitDSHolder.getOrCreateJobsGroupMap(matchingGroup);
        for (String jobName : grpJobNames) {
          JobKey jobKey = new JobKey(jobName, matchingGroup);
          if (jobFacade.containsKey(jobKey)) {
            out.add(jobKey);
          }
        }
      }

      return out;
    } finally {
      unlock();
    }
  }
