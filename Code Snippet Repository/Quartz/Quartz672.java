    public void testYearlyIntervalGetFireTimeAfter() {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        startCalendar.clear(Calendar.MILLISECOND);

        CalendarIntervalTriggerImpl yearlyTrigger = new CalendarIntervalTriggerImpl();
        yearlyTrigger.setStartTime(startCalendar.getTime());
        yearlyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.YEAR);
        yearlyTrigger.setRepeatInterval(2); // every two years;
        
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(2009, Calendar.JUNE, 1, 9, 30, 17); // jump 4 years (2 intervals)
        targetCalendar.clear(Calendar.MILLISECOND);

        List<Date> fireTimes = TriggerUtils.computeFireTimes(yearlyTrigger, null, 4);
        Date secondTime = fireTimes.get(2); // get the third fire time
        
        assertEquals("Year increment result not as expected.", targetCalendar.getTime(), secondTime);
    }
