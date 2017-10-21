    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Scheduler scheduler = context.getScheduler();
            List<Long> scheduledFires = (List<Long>)scheduler.getContext().get(SCHEDULED_TIMES_KEY);
            scheduledFires.add(context.getScheduledFireTime().getTime());
        } catch (SchedulerException e) {
            throw new JobExecutionException(e);
        }
    }
