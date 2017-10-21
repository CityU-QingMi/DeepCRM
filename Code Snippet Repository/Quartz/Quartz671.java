    public void testQTZ330DaylightSavingsCornerCase() {
        TimeZone edt = TimeZone.getTimeZone("America/New_York");

        Calendar start = Calendar.getInstance();
        start.clear();
        start.setTimeZone(edt);
        start.set(2012, Calendar.MARCH, 16, 2, 30, 0);

        Calendar after = Calendar.getInstance();
        after.clear();
        after.setTimeZone(edt);
        after.set(2013, Calendar.APRIL, 19, 2, 30, 0);

        BaseCalendar baseCalendar = new BaseCalendar(edt);

        CalendarIntervalTriggerImpl intervalTrigger = new CalendarIntervalTriggerImpl("QTZ-330", start.getTime(), null, DateBuilder.IntervalUnit.DAY, 1);
        intervalTrigger.setTimeZone(edt);
        intervalTrigger.setPreserveHourOfDayAcrossDaylightSavings(true);
        intervalTrigger.computeFirstFireTime(baseCalendar);

        Date fireTime = intervalTrigger.getFireTimeAfter(after.getTime());
        assertThat(fireTime.after(after.getTime()), is(true));
    }
