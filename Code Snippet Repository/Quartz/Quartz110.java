    public boolean notifyTriggerListenersFired(JobExecutionContext jec)
        throws SchedulerException {

        boolean vetoedExecution = false;
        
        // build a list of all trigger listeners that are to be notified...
        List<TriggerListener> triggerListeners = buildTriggerListenerList();

        // notify all trigger listeners in the list
        for(TriggerListener tl: triggerListeners) {
            try {
                if(!matchTriggerListener(tl, jec.getTrigger().getKey()))
                    continue;
                tl.triggerFired(jec.getTrigger(), jec);
                
                if(tl.vetoJobExecution(jec.getTrigger(), jec)) {
                    vetoedExecution = true;
                }
            } catch (Exception e) {
                SchedulerException se = new SchedulerException(
                        "TriggerListener '" + tl.getName()
                                + "' threw exception: " + e.getMessage(), e);
                throw se;
            }
        }
        
        return vetoedExecution;
    }
