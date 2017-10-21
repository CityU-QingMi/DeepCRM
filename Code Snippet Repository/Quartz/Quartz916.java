    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        // Call the scheduleJobInterruptMonitor and capture the ScheduledFuture in context
        try {
            // Schedule Monitor only if the job wants AutoInterruptable functionality
            if (context.getJobDetail().getJobDataMap().getBoolean(AUTO_INTERRUPTIBLE)) {
                JobInterruptMonitorPlugin monitorPlugin = (JobInterruptMonitorPlugin) context.getScheduler()
                        .getContext().get(JOB_INTERRUPT_MONITOR_KEY);
                // Get the MaxRuntime from Job Data if NOT available use DEFAULT_MAX_RUNTIME from Plugin Configuration
                long jobDataDelay  = DEFAULT_MAX_RUNTIME;

                if (context.getJobDetail().getJobDataMap().get(MAX_RUN_TIME) != null){
                     jobDataDelay = context.getJobDetail().getJobDataMap().getLong(MAX_RUN_TIME);
                }
                future = monitorPlugin.scheduleJobInterruptMonitor(context.getJobDetail().getKey(), jobDataDelay);
                getLog().debug("Job's Interrupt Monitor has been scheduled to interrupt with the delay :"
                        + DEFAULT_MAX_RUNTIME);
            }
        } catch (SchedulerException e) {
            getLog().info("Error scheduling interrupt monitor " + e.getMessage(), e);
        }
    }
