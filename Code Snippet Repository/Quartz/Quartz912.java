    public void jobExecutionVetoed(JobExecutionContext context) {
        
        if (!getLog().isInfoEnabled()) {
            return;
        } 
        
        Trigger trigger = context.getTrigger();

        Object[] args = {
            context.getJobDetail().getKey().getName(),
            context.getJobDetail().getKey().getGroup(), new java.util.Date(),
            trigger.getKey().getName(), trigger.getKey().getGroup(),
            trigger.getPreviousFireTime(), trigger.getNextFireTime(),
            Integer.valueOf(context.getRefireCount())
        };

        getLog().info(MessageFormat.format(getJobWasVetoedMessage(), args));
    }
