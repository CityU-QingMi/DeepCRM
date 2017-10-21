    private boolean notifyJobListenersComplete(JobExecutionContext jobExCtxt, JobExecutionException jobExEx) {
        try {
            qs.notifyJobListenersWasExecuted(jobExCtxt, jobExEx);
        } catch (SchedulerException se) {
            qs.notifySchedulerListenersError(
                    "Unable to notify JobListener(s) of Job that was executed: "
                            + "(error will be ignored). trigger= "
                            + jobExCtxt.getTrigger().getKey() + " job= "
                            + jobExCtxt.getJobDetail().getKey(), se);

            return false;
        }

        return true;
    }
