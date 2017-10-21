    @Override
    public ScheduleBuilder<SimpleTrigger> getScheduleBuilder() {
        
        SimpleScheduleBuilder sb = SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInMilliseconds(getRepeatInterval())
        .withRepeatCount(getRepeatCount());
        
        switch(getMisfireInstruction()) {
            case MISFIRE_INSTRUCTION_FIRE_NOW : sb.withMisfireHandlingInstructionFireNow();
            break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT : sb.withMisfireHandlingInstructionNextWithExistingCount();
            break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT : sb.withMisfireHandlingInstructionNextWithRemainingCount();
            break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT : sb.withMisfireHandlingInstructionNowWithExistingCount();
            break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT : sb.withMisfireHandlingInstructionNowWithRemainingCount();
            break;
        }
        
        return sb;
    }
