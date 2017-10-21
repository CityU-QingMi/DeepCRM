    public void notifySchedulerListenersJobDeleted(JobKey jobKey) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.jobDeleted(jobKey);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of JobAdded.",
                        e);
            }
        }
    }
