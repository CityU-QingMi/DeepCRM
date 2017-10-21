  public void testEndingAtAfterCountOf1() {
    Date startTime = DateBuilder.dateOf(0,  0, 0, 1, 1, 2011);
    DailyTimeIntervalTrigger trigger = newTrigger()
        .withIdentity("test")
        .withSchedule(
            dailyTimeIntervalSchedule()
            .withIntervalInMinutes(15)
            .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(8, 0))
            .endingDailyAfterCount(1))
        .startAt(startTime)
        .build();
    Assert.assertEquals("test", trigger.getKey().getName());
    Assert.assertEquals("DEFAULT", trigger.getKey().getGroup());
    Assert.assertEquals(IntervalUnit.MINUTE, trigger.getRepeatIntervalUnit());
    List<Date> fireTimes = TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(8, 0, 0, 17, 2, 2011), fireTimes.get(47));
    Assert.assertEquals(new TimeOfDay(8, 0), trigger.getEndTimeOfDay());
  }
