    @SuppressWarnings("")
    @Override
    protected Object getTargetObject() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("A", "B");
        
        SimpleTriggerImpl t = new SimpleTriggerImpl("SimpleTrigger", "SimpleGroup",
                "JobName", "JobGroup", START_TIME.getTime(),
                END_TIME.getTime(), 5, 1000);
        t.setCalendarName("MyCalendar");
        t.setDescription("SimpleTriggerDesc");
        t.setJobDataMap(jobDataMap);
        t.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);

        return t;
    }
