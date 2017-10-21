    public Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher)
        throws SchedulerException {
        validateState();

        if(matcher == null) {
            matcher = GroupMatcher.groupEquals(Scheduler.DEFAULT_GROUP);
        }
        
        return resources.getJobStore().getJobKeys(matcher);
    }
