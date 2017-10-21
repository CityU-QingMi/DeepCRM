    public void pauseJobs(GroupMatcher<JobKey> matcher) throws SchedulerException {
        String operation = null;
        switch (matcher.getCompareWithOperator()) {
            case EQUALS:
                operation = "pauseJobGroup";
                break;
            case STARTS_WITH:
                operation = "pauseJobsStartingWith";
                break;
            case ENDS_WITH:
                operation = "pauseJobsEndingWith";
                break;
            case CONTAINS:
                operation = "pauseJobsContaining";
            case ANYTHING:
                operation = "pauseJobsAll";
        }

        invoke(
                operation,
                new Object[] { matcher.getCompareToValue() },
                new String[] { String.class.getName() });
    }
