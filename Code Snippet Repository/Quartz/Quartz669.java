    @Override
    protected Object getTargetObject() throws Exception {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("A", "B");
        
        CalendarIntervalTriggerImpl t = new CalendarIntervalTriggerImpl();
        t.setName("test");
        t.setGroup("testGroup");
        t.setCalendarName("MyCalendar");
        t.setDescription("CronTriggerDesc");
        t.setJobDataMap(jobDataMap);
        t.setRepeatInterval(5);
        t.setRepeatIntervalUnit(IntervalUnit.DAY);

        return t;    
    }
