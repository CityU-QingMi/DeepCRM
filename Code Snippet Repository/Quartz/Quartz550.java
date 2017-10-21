    public void resetTriggerFromErrorState(final TriggerKey triggerKey) throws JobPersistenceException {

        synchronized (lock) {

            TriggerWrapper tw = triggersByKey.get(triggerKey);
            // does the trigger exist?
            if (tw == null || tw.trigger == null) {
                return;
            }
            // is the trigger in error state?
            if (tw.state != TriggerWrapper.STATE_ERROR) {
                return;
            }

            if(pausedTriggerGroups.contains(triggerKey.getGroup())) {
                tw.state = TriggerWrapper.STATE_PAUSED;
            }
            else {
                tw.state = TriggerWrapper.STATE_WAITING;
                timeTriggers.add(tw);
            }
        }
    }
