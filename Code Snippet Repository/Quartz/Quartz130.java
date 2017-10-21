    public void notifySchedulerListenersInStandbyMode() {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.schedulerInStandbyMode();
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of inStandByMode.",
                        e);
            }
        }
    }
