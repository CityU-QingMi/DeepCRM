    public Set<TriggerKey> getTriggerKeys(GroupMatcher<TriggerKey> matcher)
        throws SchedulerException {
        validateState();

        if(matcher == null) {
            matcher = GroupMatcher.groupEquals(Scheduler.DEFAULT_GROUP);
        }
        
        return resources.getJobStore().getTriggerKeys(matcher);
    }
