    public void notifySchedulerListenersError(String msg, SchedulerException se) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.schedulerError(msg, se);
            } catch (Exception e) {
                getLog()
                        .error(
                                "Error while notifying SchedulerListener of error: ",
                                e);
                getLog().error(
                        "  Original error (for notification) was: " + msg, se);
            }
        }
    }
