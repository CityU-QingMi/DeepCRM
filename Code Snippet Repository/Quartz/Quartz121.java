    public void notifySchedulerListenersPausedTrigger(TriggerKey triggerKey) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.triggerPaused(triggerKey);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of paused trigger: "
                                + triggerKey, e);
            }
        }
    }
