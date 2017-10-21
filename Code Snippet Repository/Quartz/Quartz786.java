  public void testEndTimeAfterEndTimeOfDay() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    Date endTime = dateOf(18, 0, 0, 2, 1, 2011);
    TimeOfDay endTimeOfDay = new TimeOfDay(17, 0, 0);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setEndTime(endTime);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(36, fireTimes.size());
    Assert.assertEquals(dateOf(0, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(17, 0, 0, 1, 1, 2011), fireTimes.get(17));
    Assert.assertEquals(dateOf(17, 0, 0, 2, 1, 2011), fireTimes.get(35));
  }
