    public void testExtraConstructors() throws Exception {
        // A test case for QTZ-389 - some extra constructors didn't set all parameters
        DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl(
                "triggerName", "triggerGroup", "jobName", "jobGroup",
                dateOf(8, 0, 0, 1, 1, 2012), null,
                new TimeOfDay(8, 0, 0), new TimeOfDay(17, 0, 0),
                IntervalUnit.HOUR, 1);

        Assert.assertEquals("triggerName", trigger.getName());
        Assert.assertEquals("triggerGroup", trigger.getGroup());
        Assert.assertEquals("jobName", trigger.getJobName());
        Assert.assertEquals("jobGroup", trigger.getJobGroup());
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getStartTime());
        Assert.assertEquals(null, trigger.getEndTime());
        Assert.assertEquals(new TimeOfDay(8, 0, 0), trigger.getStartTimeOfDay());
        Assert.assertEquals(new TimeOfDay(17, 0, 0), trigger.getEndTimeOfDay());
        Assert.assertEquals(IntervalUnit.HOUR, trigger.getRepeatIntervalUnit());
        Assert.assertEquals(1, trigger.getRepeatInterval());

        trigger = new DailyTimeIntervalTriggerImpl(
                "triggerName", "triggerGroup",
                dateOf(8, 0, 0, 1, 1, 2012), null,
                new TimeOfDay(8, 0, 0), new TimeOfDay(17, 0, 0),
                IntervalUnit.HOUR, 1);

        Assert.assertEquals("triggerName", trigger.getName());
        Assert.assertEquals("triggerGroup", trigger.getGroup());
        Assert.assertEquals(null, trigger.getJobName());
        Assert.assertEquals("DEFAULT", trigger.getJobGroup());
        Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2012), trigger.getStartTime());
        Assert.assertEquals(null, trigger.getEndTime());
        Assert.assertEquals(new TimeOfDay(8, 0, 0), trigger.getStartTimeOfDay());
        Assert.assertEquals(new TimeOfDay(17, 0, 0), trigger.getEndTimeOfDay());
        Assert.assertEquals(IntervalUnit.HOUR, trigger.getRepeatIntervalUnit());
        Assert.assertEquals(1, trigger.getRepeatInterval());
    }
