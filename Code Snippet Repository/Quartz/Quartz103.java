    public void resumeJobs(GroupMatcher<JobKey> matcher)
        throws SchedulerException {
        validateState();

        if(matcher == null) {
            matcher = GroupMatcher.groupEquals(Scheduler.DEFAULT_GROUP);
        }
        
        Collection<String> resumedGroups = resources.getJobStore().resumeJobs(matcher);
        notifySchedulerThread(0L);
        for (String pausedGroup : resumedGroups) {
            notifySchedulerListenersResumedJobs(pausedGroup);
        }
    }
