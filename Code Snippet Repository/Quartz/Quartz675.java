    public void testDailyIntervalGetFireTimeAfter() {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        startCalendar.clear(Calendar.MILLISECOND);

        CalendarIntervalTriggerImpl dailyTrigger = new CalendarIntervalTriggerImpl();
        dailyTrigger.setStartTime(startCalendar.getTime());
        dailyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.DAY);
        dailyTrigger.setRepeatInterval(90); // every ninety days
        
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(2005, Calendar.JUNE, 1, 9, 30, 17);
        targetCalendar.setLenient(true);
        targetCalendar.add(Calendar.DAY_OF_YEAR, 360); // jump 360 days (4 intervals)
        targetCalendar.clear(Calendar.MILLISECOND);

        List<Date> fireTimes = TriggerUtils.computeFireTimes(dailyTrigger, null, 6);
        Date fifthTime = fireTimes.get(4); // get the fifth fire time

        assertEquals("Day increment result not as expected.", targetCalendar.getTime(), fifthTime);
    }
