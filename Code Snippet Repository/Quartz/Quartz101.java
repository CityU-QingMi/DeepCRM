    public void resumeTriggers(GroupMatcher<TriggerKey> matcher)
        throws SchedulerException {
        validateState();

        if(matcher == null) {
            matcher = GroupMatcher.groupEquals(Scheduler.DEFAULT_GROUP);
        }

        Collection<String> pausedGroups = resources.getJobStore().resumeTriggers(matcher);
        notifySchedulerThread(0L);
        for (String pausedGroup : pausedGroups) {
            notifySchedulerListenersResumedTriggers(pausedGroup);
        }
    }
