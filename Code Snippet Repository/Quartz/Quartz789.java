  public void testTimeOfDayWithEndTime2() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 23, 0);
    TimeOfDay endTimeOfDay = new TimeOfDay(23, 59, 59); // edge case when endTime is last second of day, which is default too.
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());    
    Assert.assertEquals(dateOf(8, 23, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(23, 23, 0, 3, 1, 2011), fireTimes.get(47));
  }
