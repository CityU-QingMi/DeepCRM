    public void notifySchedulerListenersPausedJob(JobKey key) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.jobPaused(key);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of paused job: "
                                + key, e);
            }
        }
    }
