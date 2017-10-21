    public void notifySchedulerListenersUnscheduled(TriggerKey triggerKey) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                if(triggerKey == null)
                    sl.schedulingDataCleared();
                else
                    sl.jobUnscheduled(triggerKey);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of unscheduled job."
                                + "  Triger=" + (triggerKey == null ? "ALL DATA" : triggerKey), e);
            }
        }
    }
