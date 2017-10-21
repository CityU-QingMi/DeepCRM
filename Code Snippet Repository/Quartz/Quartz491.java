    public CalendarIntervalTriggerImpl(String name, String group, String jobName,
            String jobGroup, Date startTime, Date endTime,  
            IntervalUnit intervalUnit,  int repeatInterval) {
        super(name, group, jobName, jobGroup);

        setStartTime(startTime);
        setEndTime(endTime);
        setRepeatIntervalUnit(intervalUnit);
        setRepeatInterval(repeatInterval);
    }
