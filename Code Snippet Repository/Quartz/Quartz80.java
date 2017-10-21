    private boolean notifyTriggerListenersComplete(JobExecutionContext jobExCtxt, CompletedExecutionInstruction instCode) {
        try {
            qs.notifyTriggerListenersComplete(jobExCtxt, instCode);

        } catch (SchedulerException se) {
            qs.notifySchedulerListenersError(
                    "Unable to notify TriggerListener(s) of Job that was executed: "
                            + "(error will be ignored). trigger= "
                            + jobExCtxt.getTrigger().getKey() + " job= "
                            + jobExCtxt.getJobDetail().getKey(), se);

            return false;
        }
        if (jobExCtxt.getTrigger().getNextFireTime() == null) {
            qs.notifySchedulerListenersFinalized(jobExCtxt.getTrigger());
        }

        return true;
    }
