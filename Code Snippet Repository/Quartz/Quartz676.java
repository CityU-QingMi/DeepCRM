    public void testHourlyIntervalGetFireTimeAfter() {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        startCalendar.clear(Calendar.MILLISECOND);

        CalendarIntervalTriggerImpl yearlyTrigger = new CalendarIntervalTriggerImpl();
        yearlyTrigger.setStartTime(startCalendar.getTime());
        yearlyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.HOUR);
        yearlyTrigger.setRepeatInterval(100); // every 100 hours
        
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        targetCalendar.setLenient(true);
        targetCalendar.add(Calendar.HOUR, 400); // jump 400 hours (4 intervals)
        targetCalendar.clear(Calendar.MILLISECOND);

        List<Date> fireTimes = TriggerUtils.computeFireTimes(yearlyTrigger, null, 6);
        Date fifthTime = fireTimes.get(4); // get the fifth fire time

        assertEquals("Hour increment result not as expected.", targetCalendar.getTime(), fifthTime);
    }
