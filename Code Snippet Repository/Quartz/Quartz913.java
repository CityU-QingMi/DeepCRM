    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        if (!getLog().isInfoEnabled()) {
            return;
        } 
        
        Object[] args = {
            trigger.getKey().getName(), trigger.getKey().getGroup(),
            trigger.getPreviousFireTime(), trigger.getNextFireTime(),
            new java.util.Date(), context.getJobDetail().getKey().getName(),
            context.getJobDetail().getKey().getGroup(),
            Integer.valueOf(context.getRefireCount())
        };

        getLog().info(MessageFormat.format(getTriggerFiredMessage(), args));
    }
