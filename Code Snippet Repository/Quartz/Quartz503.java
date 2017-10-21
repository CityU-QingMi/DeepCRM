    @Deprecated
    public CronTriggerImpl(String name, String group, String jobName,
            String jobGroup, Date startTime, Date endTime, String cronExpression)
        throws ParseException {
        super(name, group, jobName, jobGroup);

        setCronExpression(cronExpression);

        if (startTime == null) {
            startTime = new Date();
        }
        setStartTime(startTime);
        if (endTime != null) {
            setEndTime(endTime);
        }
        setTimeZone(TimeZone.getDefault());

    }
