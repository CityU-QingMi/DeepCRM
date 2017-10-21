    @SuppressWarnings("")
    public T build() {

        if(scheduleBuilder == null)
            scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        MutableTrigger trig = scheduleBuilder.build();
        
        trig.setCalendarName(calendarName);
        trig.setDescription(description);
        trig.setStartTime(startTime);
        trig.setEndTime(endTime);
        if(key == null)
            key = new TriggerKey(Key.createUniqueName(null), null);
        trig.setKey(key); 
        if(jobKey != null)
            trig.setJobKey(jobKey);
        trig.setPriority(priority);
        
        if(!jobDataMap.isEmpty())
            trig.setJobDataMap(jobDataMap);
        
        return (T) trig;
    }
