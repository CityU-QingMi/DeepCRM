    public void notifySchedulerListenersResumedTriggers(String group) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.triggersResumed(group);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of resumed group: "
                                + group, e);
            }
        }
    }
