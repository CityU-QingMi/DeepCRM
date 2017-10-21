    @Deprecated
    public CronTriggerImpl(String name, String group, String jobName,
            String jobGroup, Date startTime, Date endTime,
            String cronExpression, TimeZone timeZone) throws ParseException {
        super(name, group, jobName, jobGroup);

        setCronExpression(cronExpression);

        if (startTime == null) {
            startTime = new Date();
        }
        setStartTime(startTime);
        if (endTime != null) {
            setEndTime(endTime);
        }
        if (timeZone == null) {
            setTimeZone(TimeZone.getDefault());
        } else {
            setTimeZone(timeZone);
        }
    }
