    public List<String> pauseTriggers(GroupMatcher<TriggerKey> matcher) {

        List<String> pausedGroups;
        synchronized (lock) {
            pausedGroups = new LinkedList<String>();

            StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
            switch (operator) {
                case EQUALS:
                    if(pausedTriggerGroups.add(matcher.getCompareToValue())) {
                        pausedGroups.add(matcher.getCompareToValue());
                    }
                    break;
                default :
                    for (String group : triggersByGroup.keySet()) {
                        if(operator.evaluate(group, matcher.getCompareToValue())) {
                            if(pausedTriggerGroups.add(matcher.getCompareToValue())) {
                                pausedGroups.add(group);
                            }
                        }
                    }
            }

            for (String pausedGroup : pausedGroups) {
                Set<TriggerKey> keys = getTriggerKeys(GroupMatcher.triggerGroupEquals(pausedGroup));

                for (TriggerKey key: keys) {
                    pauseTrigger(key);
                }
            }
        }

        return pausedGroups;
    }
