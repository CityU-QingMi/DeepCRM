  public void testSecondlyTriggerWithStartAndEndTime() {
    Date startTime = DateBuilder.dateOf(0,  0, 0, 1, 1, 2011);
    Date endTime = DateBuilder.dateOf(0, 0, 0, 2, 1, 2011);
    DailyTimeIntervalTrigger trigger = newTrigger().withIdentity("test", "test")
        .withSchedule(dailyTimeIntervalSchedule()
            .withIntervalInSeconds(121)
            .startingDailyAt(hourMinuteAndSecondOfDay(10, 0, 0))
            .endingDailyAt(hourMinuteAndSecondOfDay(23, 59, 59))
            .onSaturdayAndSunday())
            .startAt(startTime)
            .endAt(endTime)
            .build();
    Assert.assertEquals("test", trigger.getKey().getName());
    Assert.assertEquals("test", trigger.getKey().getGroup());
    Assert.assertEquals(true, startTime.getTime() == trigger.getStartTime().getTime());
    Assert.assertEquals(true, endTime.getTime() == trigger.getEndTime().getTime());
    Assert.assertEquals(IntervalUnit.SECOND, trigger.getRepeatIntervalUnit());
    Assert.assertEquals(121, trigger.getRepeatInterval());
    Assert.assertEquals(new TimeOfDay(10, 0, 0), trigger.getStartTimeOfDay());
    Assert.assertEquals(new TimeOfDay(23, 59, 59), trigger.getEndTimeOfDay());
    List<Date> fireTimes = TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
  }
