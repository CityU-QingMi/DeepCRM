    @Test
    public void testQTZ331FireTimeAfterBoundary() {
        Calendar start = Calendar.getInstance();
        start.clear();
        start.set(2013, Calendar.FEBRUARY, 15);

        Date startTime = start.getTime();
        start.add(Calendar.DAY_OF_MONTH, 1);
        Date triggerTime = start.getTime();

        CalendarIntervalTriggerImpl trigger = new CalendarIntervalTriggerImpl("test", startTime, null, IntervalUnit.DAY, 1);
        assertThat(trigger.getFireTimeAfter(startTime), equalTo(triggerTime));


        Date after = new Date(start.getTimeInMillis() - 500);
        assertThat(trigger.getFireTimeAfter(after), equalTo(triggerTime));
    }
