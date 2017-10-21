    public void resumeTriggers(GroupMatcher<TriggerKey> matcher) throws SchedulerException {
        String operation = null;
        switch (matcher.getCompareWithOperator()) {
            case EQUALS:
                operation = "resumeTriggerGroup";
                break;
            case CONTAINS:
                operation = "resumeTriggersContaining";
                break;
            case STARTS_WITH:
                operation = "resumeTriggersStartingWith";
                break;
            case ENDS_WITH:
                operation = "resumeTriggersEndingWith";
            case ANYTHING:
                operation = "resumeTriggersAll";
        }

        if (operation != null) {
            invoke(
                    operation,
                    new Object[] { matcher.getCompareToValue() },
                    new String[] { String.class.getName() });
        } else {
            throw new SchedulerException("Unsupported GroupMatcher kind for resuming triggers: " + matcher.getCompareWithOperator());
        }
    }
