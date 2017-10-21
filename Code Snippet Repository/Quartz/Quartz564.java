    public Collection<String> resumeJobs(GroupMatcher<JobKey> matcher) {
        Set<String> resumedGroups = new HashSet<String>();
        synchronized (lock) {
            Set<JobKey> keys = getJobKeys(matcher);

            for (String pausedJobGroup : pausedJobGroups) {
                if(matcher.getCompareWithOperator().evaluate(pausedJobGroup, matcher.getCompareToValue())) {
                    resumedGroups.add(pausedJobGroup);
                }
            }

            for (String resumedGroup : resumedGroups) {
                pausedJobGroups.remove(resumedGroup);
            }

            for (JobKey key: keys) {
                List<OperableTrigger> triggersOfJob = getTriggersForJob(key);
                for (OperableTrigger trigger: triggersOfJob) {
                    resumeTrigger(trigger.getKey());
                }
            }
        }
        return resumedGroups;
    }
