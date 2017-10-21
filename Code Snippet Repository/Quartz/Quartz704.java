  public void testRepeatCountTrigger() {
    DailyTimeIntervalTrigger trigger = newTrigger()
        .withIdentity("test")
        .withSchedule(
            dailyTimeIntervalSchedule()
            .withIntervalInHours(1)
            .withRepeatCount(9))
        .build();
    Assert.assertEquals("test", trigger.getKey().getName());
    Assert.assertEquals("DEFAULT", trigger.getKey().getGroup());
    Assert.assertEquals(IntervalUnit.HOUR, trigger.getRepeatIntervalUnit());
    Assert.assertEquals(1, trigger.getRepeatInterval());
    List<Date> fireTimes = TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 48);
    Assert.assertEquals(10, fireTimes.size());
  }
