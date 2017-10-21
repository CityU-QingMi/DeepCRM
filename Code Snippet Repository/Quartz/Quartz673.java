    public void testMonthlyIntervalGetFireTimeAfter() {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        startCalendar.clear(Calendar.MILLISECOND);

        CalendarIntervalTriggerImpl yearlyTrigger = new CalendarIntervalTriggerImpl();
        yearlyTrigger.setStartTime(startCalendar.getTime());
        yearlyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MONTH);
        yearlyTrigger.setRepeatInterval(5); // every five months
        
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        targetCalendar.setLenient(true);
        targetCalendar.add(Calendar.MONTH, 25); // jump 25 five months (5 intervals)
        targetCalendar.clear(Calendar.MILLISECOND);

        List<Date> fireTimes = TriggerUtils.computeFireTimes(yearlyTrigger, null, 6);
        Date fifthTime = fireTimes.get(5); // get the sixth fire time

        assertEquals("Month increment result not as expected.", targetCalendar.getTime(), fifthTime);
    }
