    public void notifyTriggerListenersMisfired(Trigger trigger)
        throws SchedulerException {
        // build a list of all trigger listeners that are to be notified...
        List<TriggerListener> triggerListeners = buildTriggerListenerList();

        // notify all trigger listeners in the list
        for(TriggerListener tl: triggerListeners) {
            try {
                if(!matchTriggerListener(tl, trigger.getKey()))
                    continue;
                tl.triggerMisfired(trigger);
            } catch (Exception e) {
                SchedulerException se = new SchedulerException(
                        "TriggerListener '" + tl.getName()
                                + "' threw exception: " + e.getMessage(), e);
                throw se;
            }
        }
    }    
