    public List<String> pauseJobs(GroupMatcher<JobKey> matcher) {
        List<String> pausedGroups = new LinkedList<String>();
        synchronized (lock) {

            StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
            switch (operator) {
                case EQUALS:
                    if (pausedJobGroups.add(matcher.getCompareToValue())) {
                        pausedGroups.add(matcher.getCompareToValue());
                    }
                    break;
                default :
                    for (String group : jobsByGroup.keySet()) {
                        if(operator.evaluate(group, matcher.getCompareToValue())) {
                            if (pausedJobGroups.add(group)) {
                                pausedGroups.add(group);
                            }
                        }
                    }
            }

            for (String groupName : pausedGroups) {
                for (JobKey jobKey: getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    List<OperableTrigger> triggersOfJob = getTriggersForJob(jobKey);
                    for (OperableTrigger trigger: triggersOfJob) {
                        pauseTrigger(trigger.getKey());
                    }
                }
            }
        }

        return pausedGroups;
    }
