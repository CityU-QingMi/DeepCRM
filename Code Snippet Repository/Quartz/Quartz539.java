    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

        JobKey sj = chainLinks.get(context.getJobDetail().getKey());

        if(sj == null) {
            return;
        }

        getLog().info("Job '" + context.getJobDetail().getKey() + "' will now chain to Job '" + sj + "'");

        try {
             context.getScheduler().triggerJob(sj);
        } catch(SchedulerException se) {
            getLog().error("Error encountered during chaining to Job '" + sj + "'", se);
        }
    }
