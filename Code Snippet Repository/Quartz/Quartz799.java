  public void testRepeatCount() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
    TimeOfDay endTimeOfDay = new TimeOfDay(11, 0, 0);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(72);
    trigger.setRepeatCount(7);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);    
    Assert.assertEquals(8, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(9, 12, 0, 3, 1, 2011), fireTimes.get(7));
  }
