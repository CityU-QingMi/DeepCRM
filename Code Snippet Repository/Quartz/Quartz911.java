    public void jobWasExecuted(JobExecutionContext context,
            JobExecutionException jobException) {

        Trigger trigger = context.getTrigger();
        
        Object[] args = null;
        
        if (jobException != null) {
            if (!getLog().isWarnEnabled()) {
                return;
            } 
            
            String errMsg = jobException.getMessage();
            args = 
                new Object[] {
                    context.getJobDetail().getKey().getName(),
                    context.getJobDetail().getKey().getGroup(), new java.util.Date(),
                    trigger.getKey().getName(), trigger.getKey().getGroup(),
                    trigger.getPreviousFireTime(), trigger.getNextFireTime(),
                    Integer.valueOf(context.getRefireCount()), errMsg
                };
            
            getLog().warn(MessageFormat.format(getJobFailedMessage(), args), jobException); 
        } else {
            if (!getLog().isInfoEnabled()) {
                return;
            } 
            
            String result = String.valueOf(context.getResult());
            args =
                new Object[] {
                    context.getJobDetail().getKey().getName(),
                    context.getJobDetail().getKey().getGroup(), new java.util.Date(),
                    trigger.getKey().getName(), trigger.getKey().getGroup(),
                    trigger.getPreviousFireTime(), trigger.getNextFireTime(),
                    Integer.valueOf(context.getRefireCount()), result
                };
            
            getLog().info(MessageFormat.format(getJobSuccessMessage(), args));
        }
    }
