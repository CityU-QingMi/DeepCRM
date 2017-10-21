    @Override
    protected SimplePropertiesTriggerProperties getTriggerProperties(OperableTrigger trigger) {
        DailyTimeIntervalTriggerImpl dailyTrigger = (DailyTimeIntervalTriggerImpl)trigger;
        SimplePropertiesTriggerProperties props = new SimplePropertiesTriggerProperties();
        
        props.setInt1(dailyTrigger.getRepeatInterval());
        props.setString1(dailyTrigger.getRepeatIntervalUnit().name());
        props.setInt2(dailyTrigger.getTimesTriggered());
        
        Set<Integer> days = dailyTrigger.getDaysOfWeek();
        String daysStr = join(days, ",");
        props.setString2(daysStr);

        StringBuilder timeOfDayBuffer = new StringBuilder();
        TimeOfDay startTimeOfDay = dailyTrigger.getStartTimeOfDay();
        if (startTimeOfDay != null) {
            timeOfDayBuffer.append(startTimeOfDay.getHour()).append(",");
            timeOfDayBuffer.append(startTimeOfDay.getMinute()).append(",");
            timeOfDayBuffer.append(startTimeOfDay.getSecond()).append(",");
        } else {
            timeOfDayBuffer.append(",,,");
        }
        TimeOfDay endTimeOfDay = dailyTrigger.getEndTimeOfDay();
        if (endTimeOfDay != null) {
            timeOfDayBuffer.append(endTimeOfDay.getHour()).append(",");
            timeOfDayBuffer.append(endTimeOfDay.getMinute()).append(",");
            timeOfDayBuffer.append(endTimeOfDay.getSecond());
        } else {
            timeOfDayBuffer.append(",,,");
        }
        props.setString3(timeOfDayBuffer.toString());
        
        props.setLong1(dailyTrigger.getRepeatCount());
        
        return props;
    }
