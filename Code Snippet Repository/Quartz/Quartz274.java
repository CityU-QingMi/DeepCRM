    @Override
    protected TriggerPropertyBundle getTriggerPropertyBundle(SimplePropertiesTriggerProperties props) {

        TimeZone tz = null; // if we use null, that's ok as system default tz will be used
        String tzId = props.getString2();
        if(tzId != null && tzId.trim().length() != 0) // there could be null entries from previously released versions
            tz = TimeZone.getTimeZone(tzId);
        
        ScheduleBuilder<?> sb = CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
            .withInterval(props.getInt1(), IntervalUnit.valueOf(props.getString1()))
            .inTimeZone(tz)
            .preserveHourOfDayAcrossDaylightSavings(props.isBoolean1())
            .skipDayIfHourDoesNotExist(props.isBoolean2());
        
        int timesTriggered = props.getInt2();
        
        String[] statePropertyNames = { "timesTriggered" };
        Object[] statePropertyValues = { timesTriggered };

        return new TriggerPropertyBundle(sb, statePropertyNames, statePropertyValues);
    }
