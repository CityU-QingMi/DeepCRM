    @Override
    protected void verifyMatch(Object target, Object deserialized) {
        CalendarIntervalTriggerImpl targetCalTrigger = (CalendarIntervalTriggerImpl)target;
        CalendarIntervalTriggerImpl deserializedCalTrigger = (CalendarIntervalTriggerImpl)deserialized;

        assertNotNull(deserializedCalTrigger);
        assertEquals(targetCalTrigger.getName(), deserializedCalTrigger.getName());
        assertEquals(targetCalTrigger.getGroup(), deserializedCalTrigger.getGroup());
        assertEquals(targetCalTrigger.getJobName(), deserializedCalTrigger.getJobName());
        assertEquals(targetCalTrigger.getJobGroup(), deserializedCalTrigger.getJobGroup());
//        assertEquals(targetCronTrigger.getStartTime(), deserializedCronTrigger.getStartTime());
        assertEquals(targetCalTrigger.getEndTime(), deserializedCalTrigger.getEndTime());
        assertEquals(targetCalTrigger.getCalendarName(), deserializedCalTrigger.getCalendarName());
        assertEquals(targetCalTrigger.getDescription(), deserializedCalTrigger.getDescription());
        assertEquals(targetCalTrigger.getJobDataMap(), deserializedCalTrigger.getJobDataMap());
        assertEquals(targetCalTrigger.getRepeatInterval(), deserializedCalTrigger.getRepeatInterval());
        assertEquals(targetCalTrigger.getRepeatIntervalUnit(), deserializedCalTrigger.getRepeatIntervalUnit());
        
    }
