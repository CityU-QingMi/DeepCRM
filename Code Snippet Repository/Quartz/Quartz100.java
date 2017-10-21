    public void pauseJobs(GroupMatcher<JobKey> groupMatcher)
        throws SchedulerException {
        validateState();

        if(groupMatcher == null) {
            groupMatcher = GroupMatcher.groupEquals(Scheduler.DEFAULT_GROUP);
        }
        
        Collection<String> pausedGroups = resources.getJobStore().pauseJobs(groupMatcher);
        notifySchedulerThread(0L);
        for (String pausedGroup : pausedGroups) {
            notifySchedulerListenersPausedJobs(pausedGroup);
        }
    }
