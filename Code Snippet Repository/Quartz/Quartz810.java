  public void testStartTimeBeforeStartTimeOfDayOnInvalidDay() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011); // Jan 1, 2011 was a saturday...
    TimeOfDay startTimeOfDay = new TimeOfDay(8, 0, 0);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    Set<Integer> daysOfWeek = new HashSet<Integer>();
    daysOfWeek.add(DateBuilder.MONDAY);
    daysOfWeek.add(DateBuilder.TUESDAY);
    daysOfWeek.add(DateBuilder.WEDNESDAY);
    daysOfWeek.add(DateBuilder.THURSDAY);
    daysOfWeek.add(DateBuilder.FRIDAY);
    trigger.setDaysOfWeek(daysOfWeek);
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(startTimeOfDay);
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60);
    
    Assert.assertEquals(dateOf(8, 0, 0, 3, 1, 2011), trigger.getFireTimeAfter(dateOf(6, 0, 0, 22, 5, 2010)));

    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, null, 48);
    Assert.assertEquals(48, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 0, 3, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(23, 0, 0, 5, 1, 2011), fireTimes.get(47));
  }
