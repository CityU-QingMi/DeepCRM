    public DailyTimeIntervalTriggerImpl(String name, String group, Date startTime,
            Date endTime, TimeOfDay startTimeOfDay, TimeOfDay endTimeOfDay, 
            IntervalUnit intervalUnit,  int repeatInterval) {
        super(name, group);

        setStartTime(startTime);
        setEndTime(endTime);
        setRepeatIntervalUnit(intervalUnit);
        setRepeatInterval(repeatInterval);
        setStartTimeOfDay(startTimeOfDay);
        setEndTimeOfDay(endTimeOfDay);
    }
