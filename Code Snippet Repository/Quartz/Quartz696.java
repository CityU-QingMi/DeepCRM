    @Override
    protected void verifyMatch(Object target, Object deserialized) {
        CronTriggerImpl targetCronTrigger = (CronTriggerImpl)target;
        CronTriggerImpl deserializedCronTrigger = (CronTriggerImpl)deserialized;

        assertNotNull(deserializedCronTrigger);
        assertEquals(targetCronTrigger.getName(), deserializedCronTrigger.getName());
        assertEquals(targetCronTrigger.getGroup(), deserializedCronTrigger.getGroup());
        assertEquals(targetCronTrigger.getJobName(), deserializedCronTrigger.getJobName());
        assertEquals(targetCronTrigger.getJobGroup(), deserializedCronTrigger.getJobGroup());
//        assertEquals(targetCronTrigger.getStartTime(), deserializedCronTrigger.getStartTime());
        assertEquals(targetCronTrigger.getEndTime(), deserializedCronTrigger.getEndTime());
        assertEquals(targetCronTrigger.getCalendarName(), deserializedCronTrigger.getCalendarName());
        assertEquals(targetCronTrigger.getDescription(), deserializedCronTrigger.getDescription());
        assertEquals(targetCronTrigger.getJobDataMap(), deserializedCronTrigger.getJobDataMap());
        assertEquals(targetCronTrigger.getCronExpression(), deserializedCronTrigger.getCronExpression());
    }
