    public void testMinutelyIntervalGetFireTimeAfter() {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        startCalendar.clear(Calendar.MILLISECOND);

        CalendarIntervalTriggerImpl yearlyTrigger = new CalendarIntervalTriggerImpl();
        yearlyTrigger.setStartTime(startCalendar.getTime());
        yearlyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
        yearlyTrigger.setRepeatInterval(100); // every 100 minutes
        
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        targetCalendar.setLenient(true);
        targetCalendar.add(Calendar.MINUTE, 400); // jump 400 minutes (4 intervals)
        targetCalendar.clear(Calendar.MILLISECOND);

        List<Date> fireTimes = TriggerUtils.computeFireTimes(yearlyTrigger, null, 6);
        Date fifthTime = fireTimes.get(4); // get the fifth fire time

        assertEquals("Minutes increment result not as expected.", targetCalendar.getTime(), fifthTime);
    }
