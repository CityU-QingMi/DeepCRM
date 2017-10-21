  public void testTimeOfDayWithEndTimeOddInterval() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    Date endTime = dateOf(0, 0, 0, 4, 1, 2011);
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
    TimeOfDay endTimeOfDay = new TimeOfDay(10, 0, 0);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setEndTime(endTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(23);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(18, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(9, 55, 0, 1, 1, 2011), fireTimes.get(5));
    Assert.assertEquals(dateOf(9, 55, 0, 3, 1, 2011), fireTimes.get(17));
  }
