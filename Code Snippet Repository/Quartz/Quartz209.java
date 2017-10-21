    public void pauseTriggers(GroupMatcher<TriggerKey> matcher) throws SchedulerException {
        String operation = null;
        switch (matcher.getCompareWithOperator()) {
            case EQUALS:
                operation = "pauseTriggerGroup";
                break;
            case CONTAINS:
                operation = "pauseTriggersContaining";
                break;
            case STARTS_WITH:
                operation = "pauseTriggersStartingWith";
                break;
            case ENDS_WITH:
                operation = "pauseTriggersEndingWith";
            case ANYTHING:
                operation = "pauseTriggersAll";
        }

        if (operation != null) {
            invoke(
                    operation,
                    new Object[] { matcher.getCompareToValue() },
                    new String[] { String.class.getName() });
        } else {
            throw new SchedulerException("Unsupported GroupMatcher kind for pausing triggers: " + matcher.getCompareWithOperator());
        }
    }
