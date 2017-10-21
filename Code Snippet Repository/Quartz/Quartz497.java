    @Deprecated
    public CronTriggerImpl(String name, String group, String cronExpression)
        throws ParseException {
        
        super(name, group);

        setCronExpression(cronExpression);

        setStartTime(new Date());
        setTimeZone(TimeZone.getDefault());
    }
