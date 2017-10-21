    public void testGetFireTime() throws Exception {
        Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
        TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
        TimeOfDay endTimeOfDay = new TimeOfDay(13, 0, 0);
        DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
        trigger.setStartTime(startTime);
        trigger.setStartTimeOfDay(startTimeOfDay);
        trigger.setEndTimeOfDay(endTimeOfDay);
        trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.HOUR);
        trigger.setRepeatInterval(1);

        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), trigger.getFireTimeAfter(dateOf(0, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), trigger.getFireTimeAfter(dateOf(7, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), trigger.getFireTimeAfter(dateOf(7, 59, 59, 1, 1, 2011)));
        Assert.assertEquals(dateOf(9, 0, 0, 1, 1, 2011), trigger.getFireTimeAfter(dateOf(8, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(10, 0, 0, 1, 1, 2011), trigger.getFireTimeAfter(dateOf(9, 0, 0, 1, 1, 2011)));
        Assert.assertEquals(dateOf(13, 0, 0, 1, 1, 2011), trigger.getFireTimeAfter(dateOf(12, 59, 59, 1, 1, 2011)));
        Assert.assertEquals(dateOf(8, 0, 0, 2, 1, 2011), trigger.getFireTimeAfter(dateOf(13, 0, 0, 1, 1, 2011)));
    }
