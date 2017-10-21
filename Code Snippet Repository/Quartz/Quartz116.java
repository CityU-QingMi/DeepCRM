    public void notifyJobListenersWasExecuted(JobExecutionContext jec,
            JobExecutionException je) throws SchedulerException {
        // build a list of all job listeners that are to be notified...
        List<JobListener> jobListeners = buildJobListenerList();

        // notify all job listeners
        for(JobListener jl: jobListeners) {
            try {
                if(!matchJobListener(jl, jec.getJobDetail().getKey()))
                    continue;
                jl.jobWasExecuted(jec, je);
            } catch (Exception e) {
                SchedulerException se = new SchedulerException(
                        "JobListener '" + jl.getName() + "' threw exception: "
                                + e.getMessage(), e);
                throw se;
            }
        }
    }
