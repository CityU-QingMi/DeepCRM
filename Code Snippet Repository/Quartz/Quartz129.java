    public void notifySchedulerListenersResumedJobs(String group) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.jobsResumed(group);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of resumed job group: "
                                + group, e);
            }
        }
    }
