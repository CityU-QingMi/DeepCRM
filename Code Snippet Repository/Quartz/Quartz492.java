    @Override
    public ScheduleBuilder<CalendarIntervalTrigger> getScheduleBuilder() {
        
        CalendarIntervalScheduleBuilder cb = CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                .withInterval(getRepeatInterval(), getRepeatIntervalUnit());
            
        switch(getMisfireInstruction()) {
            case MISFIRE_INSTRUCTION_DO_NOTHING : cb.withMisfireHandlingInstructionDoNothing();
            break;
            case MISFIRE_INSTRUCTION_FIRE_ONCE_NOW : cb.withMisfireHandlingInstructionFireAndProceed();
            break;
        }
        
        return cb;
    }
