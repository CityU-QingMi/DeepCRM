  public void testEndTimeWithoutEndTimeOfDay() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    Date endTime = dateOf(22, 0, 0, 2, 1, 2011);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setEndTime(endTime);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(47, fireTimes.size());
    Assert.assertEquals(dateOf(0, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(22, 0, 0, 2, 1, 2011), fireTimes.get(46));
  }
