    public void testMisfireInstructionValidity() throws ParseException {
        CalendarIntervalTriggerImpl trigger = new CalendarIntervalTriggerImpl();

        try {
            trigger.setMisfireInstruction(Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);
            trigger.setMisfireInstruction(Trigger.MISFIRE_INSTRUCTION_SMART_POLICY);
            trigger.setMisfireInstruction(CalendarIntervalTriggerImpl.MISFIRE_INSTRUCTION_DO_NOTHING);
            trigger.setMisfireInstruction(CalendarIntervalTriggerImpl.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
        }
        catch(Exception e) {
            fail("Unexpected exception while setting misfire instruction.");
        }
        
        try {
            trigger.setMisfireInstruction(CalendarIntervalTriggerImpl.MISFIRE_INSTRUCTION_DO_NOTHING + 1);
            
            fail("Expected exception while setting invalid misfire instruction but did not get it.");
        }
        catch(Exception e) {
        }
    }
