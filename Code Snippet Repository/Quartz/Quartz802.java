    public void testGetFireTimeWithDateBeforeStartTime() throws Exception {
        Date startTime = dateOf(0, 0, 0, 1, 1, 2012);
        TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
        TimeOfDay endTimeOfDay = new TimeOfDay(13, 0, 0);
        DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
        trigger.setStartTime(startTime);
        trigger.setStartTimeOfDay(startTimeOfDay);
        trigger.setEndTimeOfDay(endTimeOfDay);
        trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.HOUR);
        trigger.setRepeatInterval(1);

        // NOTE that if you pass a date past the startTime, you will get the first firing on or after the startTime back!
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(0, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(7, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(7, 59, 59, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(8, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(9, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(12, 59, 59, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(13, 0, 0, 1, 1, 2011)));

        // Now try some test times at or after startTime
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getFireTimeAfter(dateOf(0, 0, 0, 1, 1, 2012)));
        Assert.assertEquals(dateOf(8, 0, 0, 2, 1, 2012), trigger.getFireTimeAfter(dateOf(13, 0, 0, 1, 1, 2012)));
    }
