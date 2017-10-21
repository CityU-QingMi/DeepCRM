    public boolean interrupt(JobKey jobKey) throws UnableToInterruptJobException  {
        try {
            return (Boolean)invoke(
                    "interruptJob",
                    new Object[] { jobKey.getName(), jobKey.getGroup() },
                    new String[] { String.class.getName(), String.class.getName() });
        } catch (SchedulerException se) {
            throw new UnableToInterruptJobException(se);
        }
    }
