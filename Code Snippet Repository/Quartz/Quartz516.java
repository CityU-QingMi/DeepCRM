    public DailyTimeIntervalTriggerImpl(String name, String group, String jobName,
            String jobGroup, Date startTime, Date endTime, 
            TimeOfDay startTimeOfDay, TimeOfDay endTimeOfDay,
            IntervalUnit intervalUnit,  int repeatInterval) {
        super(name, group, jobName, jobGroup);

        setStartTime(startTime);
        setEndTime(endTime);
        setRepeatIntervalUnit(intervalUnit);
        setRepeatInterval(repeatInterval);
        setStartTimeOfDay(startTimeOfDay);
        setEndTimeOfDay(endTimeOfDay);
    }
