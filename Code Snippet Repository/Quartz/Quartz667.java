    public void testFinalFireTimes() {

        
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2010, Calendar.MARCH, 12, 9, 0, 0);
        startCalendar.clear(Calendar.MILLISECOND);

        CalendarIntervalTriggerImpl dailyTrigger = new CalendarIntervalTriggerImpl();
        dailyTrigger.setStartTime(startCalendar.getTime());
        dailyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.DAY);
        dailyTrigger.setRepeatInterval(5); // every 5 days
        
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startCalendar.getTime());
        endCalendar.setLenient(true);
        endCalendar.add(Calendar.DAY_OF_YEAR, 10); // jump 10 days (2 intervals)
        endCalendar.clear(Calendar.MILLISECOND);
        dailyTrigger.setEndTime(endCalendar.getTime());

        Date testTime = dailyTrigger.getFinalFireTime();

        assertEquals("Final fire time not computed correctly for day interval.", endCalendar.getTime(), testTime);

        
        startCalendar = Calendar.getInstance();
        startCalendar.set(2010, Calendar.MARCH, 12, 9, 0, 0);
        startCalendar.clear(Calendar.MILLISECOND);

        dailyTrigger = new CalendarIntervalTriggerImpl();
        dailyTrigger.setStartTime(startCalendar.getTime());
        dailyTrigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
        dailyTrigger.setRepeatInterval(5); // every 5 minutes
        
        endCalendar = Calendar.getInstance();
        endCalendar.setTime(startCalendar.getTime());
        endCalendar.setLenient(true);
        endCalendar.add(Calendar.DAY_OF_YEAR, 15); // jump 15 days 
        endCalendar.add(Calendar.MINUTE,-2); // back up two minutes
        endCalendar.clear(Calendar.MILLISECOND);
        dailyTrigger.setEndTime(endCalendar.getTime());

        testTime = dailyTrigger.getFinalFireTime();

        assertTrue("Final fire time not computed correctly for minutely interval.", (endCalendar.getTime().after(testTime)));

        endCalendar.add(Calendar.MINUTE,-3); // back up three more minutes
        
        assertTrue("Final fire time not computed correctly for minutely interval.", (endCalendar.getTime().equals(testTime)));
    }
