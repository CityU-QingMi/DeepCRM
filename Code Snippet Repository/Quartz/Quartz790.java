  public void testAllDaysOfTheWeek() throws Exception {
    Set<Integer> daysOfWeek = DailyTimeIntervalScheduleBuilder.ALL_DAYS_OF_THE_WEEK;
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011); // SAT
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
    TimeOfDay endTimeOfDay = new TimeOfDay(17, 0, 0);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setEndTimeOfDay(endTimeOfDay);
    trigger.setDaysOfWeek(daysOfWeek);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60);
    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(17, 0, 0, 1, 1, 2011), fireTimes.get(9)); // The 10th hours is the end of day.
    Assert.assertEquals(dateOf(15, 0, 0, 5, 1, 2011), fireTimes.get(47));
  }
