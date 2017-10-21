    @Deprecated
    public SimpleTriggerImpl(String name, String group, Date startTime,
            Date endTime, int repeatCount, long repeatInterval) {
        super(name, group);

        setStartTime(startTime);
        setEndTime(endTime);
        setRepeatCount(repeatCount);
        setRepeatInterval(repeatInterval);
    }
