  public void testSecondInterval() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 2);
    TimeOfDay endTimeOfDay = new TimeOfDay(13, 30, 0);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.SECOND);
    trigger.setRepeatInterval(72);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 2, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(8, 56, 26, 1, 1, 2011), fireTimes.get(47));
  }
