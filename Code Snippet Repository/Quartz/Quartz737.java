    public void testUpdateAfterMisfire() {
        
        Calendar startTime = Calendar.getInstance();
        startTime.set(2005, Calendar.JULY, 5, 9, 0, 0);
        
        Calendar endTime = Calendar.getInstance();
        endTime.set(2005, Calendar.JULY, 5, 10, 0, 0);
        
        SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
        simpleTrigger.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT);
        simpleTrigger.setRepeatCount(5);
        simpleTrigger.setStartTime(startTime.getTime());
        simpleTrigger.setEndTime(endTime.getTime());
        
        simpleTrigger.updateAfterMisfire(null);
        assertEquals(startTime.getTime(), simpleTrigger.getStartTime());
        assertEquals(endTime.getTime(), simpleTrigger.getEndTime());
        assertNull(simpleTrigger.getNextFireTime());
    }
