    public void notifyTriggerListenersComplete(JobExecutionContext jec,
            CompletedExecutionInstruction instCode) throws SchedulerException {
        // build a list of all trigger listeners that are to be notified...
        List<TriggerListener> triggerListeners = buildTriggerListenerList();

        // notify all trigger listeners in the list
        for(TriggerListener tl: triggerListeners) {
            try {
                if(!matchTriggerListener(tl, jec.getTrigger().getKey()))
                    continue;
                tl.triggerComplete(jec.getTrigger(), jec, instCode);
            } catch (Exception e) {
                SchedulerException se = new SchedulerException(
                        "TriggerListener '" + tl.getName()
                                + "' threw exception: " + e.getMessage(), e);
                throw se;
            }
        }
    }
