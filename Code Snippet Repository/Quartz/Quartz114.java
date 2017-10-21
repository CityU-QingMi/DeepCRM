    public void notifyJobListenersToBeExecuted(JobExecutionContext jec)
        throws SchedulerException {
        // build a list of all job listeners that are to be notified...
        List<JobListener> jobListeners = buildJobListenerList();

        // notify all job listeners
        for(JobListener jl: jobListeners) {
            try {
                if(!matchJobListener(jl, jec.getJobDetail().getKey()))
                    continue;
                jl.jobToBeExecuted(jec);
            } catch (Exception e) {
                SchedulerException se = new SchedulerException(
                        "JobListener '" + jl.getName() + "' threw exception: "
                                + e.getMessage(), e);
                throw se;
            }
        }
    }
