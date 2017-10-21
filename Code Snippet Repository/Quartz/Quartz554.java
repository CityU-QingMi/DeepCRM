    public Set<TriggerKey> getTriggerKeys(GroupMatcher<TriggerKey> matcher) {
        Set<TriggerKey> outList = null;
        synchronized (lock) {

            StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
            String compareToValue = matcher.getCompareToValue();

            switch(operator) {
                case EQUALS:
                    HashMap<TriggerKey, TriggerWrapper> grpMap = triggersByGroup.get(compareToValue);
                    if (grpMap != null) {
                        outList = new HashSet<TriggerKey>();

                        for (TriggerWrapper tw : grpMap.values()) {

                            if (tw != null) {
                                outList.add(tw.trigger.getKey());
                            }
                        }
                    }
                    break;

                default:
                    for (Map.Entry<String, HashMap<TriggerKey, TriggerWrapper>> entry : triggersByGroup.entrySet()) {
                        if(operator.evaluate(entry.getKey(), compareToValue) && entry.getValue() != null) {
                            if(outList == null) {
                                outList = new HashSet<TriggerKey>();
                            }
                            for (TriggerWrapper triggerWrapper : entry.getValue().values()) {
                                if(triggerWrapper != null) {
                                    outList.add(triggerWrapper.trigger.getKey());
                                }
                            }
                        }
                    }
            }
        }

        return outList == null ? Collections.<TriggerKey>emptySet() : outList;
    }
