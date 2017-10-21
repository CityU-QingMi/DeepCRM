  public void testMonOnly() throws Exception {
    Set<Integer> daysOfWeek = new HashSet<Integer>();
    daysOfWeek.add(Calendar.MONDAY);
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011); // SAT(7)
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
    Assert.assertEquals(dateOf(8, 0, 0, 3, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(Calendar.MONDAY, getDayOfWeek(fireTimes.get(0)));
    Assert.assertEquals(dateOf(8, 0, 0, 10, 1, 2011), fireTimes.get(10));
    Assert.assertEquals(Calendar.MONDAY, getDayOfWeek(fireTimes.get(10)));
    Assert.assertEquals(dateOf(15, 0, 0, 31, 1, 2011), fireTimes.get(47));
    Assert.assertEquals(Calendar.MONDAY, getDayOfWeek(fireTimes.get(47)));
  }
