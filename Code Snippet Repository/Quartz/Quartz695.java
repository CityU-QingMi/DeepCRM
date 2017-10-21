    @Override
    protected Object getTargetObject() throws Exception {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("A", "B");
        
        CronTriggerImpl t = new CronTriggerImpl();
        t.setName("test");
        t.setGroup("testGroup");
        t.setCronExpression("0 0 12 * * ?");
        t.setCalendarName("MyCalendar");
        t.setDescription("CronTriggerDesc");
        t.setJobDataMap(jobDataMap);

        return t;
    }
