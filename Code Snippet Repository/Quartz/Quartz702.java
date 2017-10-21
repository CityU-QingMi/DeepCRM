  public void testMinutelyTriggerWithTimeOfDay() {
    DailyTimeIntervalTrigger trigger = newTrigger().withIdentity("test", "group")
        .withSchedule(dailyTimeIntervalSchedule()
            .withIntervalInMinutes(72)
            .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(8, 0))
            .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17, 0))
            .onMondayThroughFriday())
            .build();
    Assert.assertEquals("test", trigger.getKey().getName());
    Assert.assertEquals("group", trigger.getKey().getGroup());
    Assert.assertEquals(true, new Date().getTime() >= trigger.getStartTime().getTime());
    Assert.assertEquals(true, null == trigger.getEndTime());
    Assert.assertEquals(IntervalUnit.MINUTE, trigger.getRepeatIntervalUnit());
    Assert.assertEquals(72, trigger.getRepeatInterval());
    Assert.assertEquals(new TimeOfDay(8, 0), trigger.getStartTimeOfDay());
    Assert.assertEquals(new TimeOfDay(17, 0), trigger.getEndTimeOfDay());
    List<Date> fireTimes = TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
  }
