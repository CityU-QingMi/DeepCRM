    public List<String> resumeTriggers(GroupMatcher<TriggerKey> matcher) {
        Set<String> groups = new HashSet<String>();

        synchronized (lock) {
            Set<TriggerKey> keys = getTriggerKeys(matcher);

            for (TriggerKey triggerKey: keys) {
                groups.add(triggerKey.getGroup());
                if(triggersByKey.get(triggerKey) != null) {
                    String jobGroup = triggersByKey.get(triggerKey).jobKey.getGroup();
                    if(pausedJobGroups.contains(jobGroup)) {
                        continue;
                    }
                }
                resumeTrigger(triggerKey);
            }

            // Find all matching paused trigger groups, and then remove them.
            StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
            LinkedList<String> pausedGroups = new LinkedList<String>();
            String matcherGroup = matcher.getCompareToValue();
            switch (operator) {
                case EQUALS:
                    if(pausedTriggerGroups.contains(matcherGroup)) {
                        pausedGroups.add(matcher.getCompareToValue());
                    }
                    break;
                default :
                    for (String group : pausedTriggerGroups) {
                        if(operator.evaluate(group, matcherGroup)) {
                            pausedGroups.add(group);
                        }
                    }
            }
            for (String pausedGroup : pausedGroups) {
                pausedTriggerGroups.remove(pausedGroup);
            }
        }

        return new ArrayList<String>(groups);
    }
