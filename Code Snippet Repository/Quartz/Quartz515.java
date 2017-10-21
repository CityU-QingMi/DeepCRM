    @Override
    public ScheduleBuilder<DailyTimeIntervalTrigger> getScheduleBuilder() {
        
        DailyTimeIntervalScheduleBuilder cb = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .withInterval(getRepeatInterval(), getRepeatIntervalUnit())
                .onDaysOfTheWeek(getDaysOfWeek()).startingDailyAt(getStartTimeOfDay()).endingDailyAt(getEndTimeOfDay());
            
        switch(getMisfireInstruction()) {
            case MISFIRE_INSTRUCTION_DO_NOTHING : cb.withMisfireHandlingInstructionDoNothing();
            break;
            case MISFIRE_INSTRUCTION_FIRE_ONCE_NOW : cb.withMisfireHandlingInstructionFireAndProceed();
            break;
        }
        
        return cb;
    }
