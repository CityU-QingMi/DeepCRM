    public void resumeTrigger(TriggerKey triggerKey) {

        synchronized (lock) {
            TriggerWrapper tw = triggersByKey.get(triggerKey);
    
            // does the trigger exist?
            if (tw == null || tw.trigger == null) {
                return;
            }
    
            OperableTrigger trig = tw.getTrigger();
    
            // if the trigger is not paused resuming it does not make sense...
            if (tw.state != TriggerWrapper.STATE_PAUSED &&
                    tw.state != TriggerWrapper.STATE_PAUSED_BLOCKED) {
                return;
            }

            if(blockedJobs.contains( trig.getJobKey() )) {
                tw.state = TriggerWrapper.STATE_BLOCKED;
            } else {
                tw.state = TriggerWrapper.STATE_WAITING;
            }

            applyMisfire(tw);

            if (tw.state == TriggerWrapper.STATE_WAITING) {
                timeTriggers.add(tw);
            }
        }
    }
