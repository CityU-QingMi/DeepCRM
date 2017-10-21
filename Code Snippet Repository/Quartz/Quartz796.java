  public void testHourInterval() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    Date endTime = dateOf(13, 0, 0, 15, 1, 2011);
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 1, 15);
    TimeOfDay endTimeOfDay = new TimeOfDay(16, 1, 15);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setEndTime(endTime);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.HOUR);
    trigger.setRepeatInterval(2);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
    Assert.assertEquals(dateOf(8, 1, 15, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(12, 1, 15, 10, 1, 2011), fireTimes.get(47));
  }
