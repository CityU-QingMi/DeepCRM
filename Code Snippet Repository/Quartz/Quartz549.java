    public TriggerState getTriggerState(TriggerKey triggerKey) throws JobPersistenceException {
        synchronized(lock) {
            TriggerWrapper tw = triggersByKey.get(triggerKey);
            
            if (tw == null) {
                return TriggerState.NONE;
            }
    
            if (tw.state == TriggerWrapper.STATE_COMPLETE) {
                return TriggerState.COMPLETE;
            }
    
            if (tw.state == TriggerWrapper.STATE_PAUSED) {
                return TriggerState.PAUSED;
            }
    
            if (tw.state == TriggerWrapper.STATE_PAUSED_BLOCKED) {
                return TriggerState.PAUSED;
            }
    
            if (tw.state == TriggerWrapper.STATE_BLOCKED) {
                return TriggerState.BLOCKED;
            }
    
            if (tw.state == TriggerWrapper.STATE_ERROR) {
                return TriggerState.ERROR;
            }
    
            return TriggerState.NORMAL;
        }
    }
