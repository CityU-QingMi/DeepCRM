    @Override
    protected void verifyMatch(Object target, Object deserialized) {
        SimpleTriggerImpl targetSimpleTrigger = (SimpleTriggerImpl)target;
        SimpleTriggerImpl deserializedSimpleTrigger = (SimpleTriggerImpl)deserialized;
        
        assertNotNull(deserializedSimpleTrigger);
        assertEquals(targetSimpleTrigger.getName(), deserializedSimpleTrigger.getName());
        assertEquals(targetSimpleTrigger.getGroup(), deserializedSimpleTrigger.getGroup());
        assertEquals(targetSimpleTrigger.getJobName(), deserializedSimpleTrigger.getJobName());
        assertEquals(targetSimpleTrigger.getJobGroup(), deserializedSimpleTrigger.getJobGroup());
        assertEquals(targetSimpleTrigger.getStartTime(), deserializedSimpleTrigger.getStartTime());
        assertEquals(targetSimpleTrigger.getEndTime(), deserializedSimpleTrigger.getEndTime());
        assertEquals(targetSimpleTrigger.getRepeatCount(), deserializedSimpleTrigger.getRepeatCount());
        assertEquals(targetSimpleTrigger.getRepeatInterval(), deserializedSimpleTrigger.getRepeatInterval());
        assertEquals(targetSimpleTrigger.getCalendarName(), deserializedSimpleTrigger.getCalendarName());
        assertEquals(targetSimpleTrigger.getDescription(), deserializedSimpleTrigger.getDescription());
        assertEquals(targetSimpleTrigger.getJobDataMap(), deserializedSimpleTrigger.getJobDataMap());
        assertEquals(targetSimpleTrigger.getMisfireInstruction(), deserializedSimpleTrigger.getMisfireInstruction());
    }
