    public CompletedExecutionInstruction executionComplete(JobExecutionContext context,
                                          JobExecutionException result)
    {
        if (result != null && result.refireImmediately()) {
            return CompletedExecutionInstruction.RE_EXECUTE_JOB;
        }
    
        if (result != null && result.unscheduleFiringTrigger()) {
            return CompletedExecutionInstruction.SET_TRIGGER_COMPLETE;
        }
    
        if (result != null && result.unscheduleAllTriggers()) {
            return CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_COMPLETE;
        }
    
        if (!mayFireAgain()) {
            return CompletedExecutionInstruction.DELETE_TRIGGER;
        }
    
        return CompletedExecutionInstruction.NOOP;
    }
