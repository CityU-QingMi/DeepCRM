    @Deprecated
    public SimpleTriggerImpl(String name, String group, String jobName,
            String jobGroup, Date startTime, Date endTime, int repeatCount,
            long repeatInterval) {
        super(name, group, jobName, jobGroup);

        setStartTime(startTime);
        setEndTime(endTime);
        setRepeatCount(repeatCount);
        setRepeatInterval(repeatInterval);
    }
