    public TriggerBuilder<T> getTriggerBuilder() {
        return TriggerBuilder.newTrigger()
            .forJob(getJobKey())
            .modifiedByCalendar(getCalendarName())
            .usingJobData(getJobDataMap())
            .withDescription(getDescription())
            .endAt(getEndTime())
            .withIdentity(getKey())
            .withPriority(getPriority())
            .startAt(getStartTime())
            .withSchedule(getScheduleBuilder());
    }
