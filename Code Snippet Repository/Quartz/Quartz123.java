    public void notifySchedulerListenersResumedTrigger(TriggerKey key) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.triggerResumed(key);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of resumed trigger: "
                                + key, e);
            }
        }
    }
