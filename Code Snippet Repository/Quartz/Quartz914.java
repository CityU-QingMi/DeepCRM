    public void triggerMisfired(Trigger trigger) {
        if (!getLog().isInfoEnabled()) {
            return;
        } 
        
        Object[] args = {
            trigger.getKey().getName(), trigger.getKey().getGroup(),
            trigger.getPreviousFireTime(), trigger.getNextFireTime(),
            new java.util.Date(), trigger.getJobKey().getName(),
            trigger.getJobKey().getGroup()
        };

        getLog().info(MessageFormat.format(getTriggerMisfiredMessage(), args));
    }
