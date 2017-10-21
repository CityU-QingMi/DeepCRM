    @Override
    public MutableTrigger build() {

        CalendarIntervalTriggerImpl st = new CalendarIntervalTriggerImpl();
        st.setRepeatInterval(interval);
        st.setRepeatIntervalUnit(intervalUnit);
        st.setMisfireInstruction(misfireInstruction);
        st.setTimeZone(timeZone);
        st.setPreserveHourOfDayAcrossDaylightSavings(preserveHourOfDayAcrossDaylightSavings);
        st.setSkipDayIfHourDoesNotExist(skipDayIfHourDoesNotExist);

        return st;
    }
