    public void testGetFireTimeWhenStartTimeAndTimeOfDayIsSame() throws Exception {
        // A test case for QTZ-369
        Date startTime = dateOf(8, 0, 0, 1, 1, 2012);
        TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
        TimeOfDay endTimeOfDay = new TimeOfDay(13, 0, 0);
        DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
        trigger.setStartTime(startTime);
        trigger.setStartTimeOfDay(startTimeOfDay);
        trigger.setEndTimeOfDay(endTimeOfDay);
        trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.HOUR);
        trigger.setRepeatInterval(1);

        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(0, 0, 0, 1, 1, 2012)));
    }
