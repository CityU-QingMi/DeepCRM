    @Override
    public MutableTrigger build() {

        DailyTimeIntervalTriggerImpl st = new DailyTimeIntervalTriggerImpl();
        st.setRepeatInterval(interval);
        st.setRepeatIntervalUnit(intervalUnit);
        st.setMisfireInstruction(misfireInstruction);
        st.setRepeatCount(repeatCount);
        
        if(daysOfWeek != null)
            st.setDaysOfWeek(daysOfWeek);
        else
            st.setDaysOfWeek(ALL_DAYS_OF_THE_WEEK);

        if(startTimeOfDay != null)
            st.setStartTimeOfDay(startTimeOfDay);
        else
            st.setStartTimeOfDay(TimeOfDay.hourAndMinuteOfDay(0, 0));

        if(endTimeOfDay != null)
            st.setEndTimeOfDay(endTimeOfDay);
        else
            st.setEndTimeOfDay(TimeOfDay.hourMinuteAndSecondOfDay(23, 59, 59));
        
        return st;
    }
