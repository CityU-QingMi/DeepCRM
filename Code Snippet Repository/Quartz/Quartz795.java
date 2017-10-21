  public void testQuartzCalendarExclusion() throws Exception {
    Date startTime = dateOf(0, 0, 0, 1, 1, 2011);
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTime(startTime);
    trigger.setStartTimeOfDay(new TimeOfDay(8, 0));
    trigger.setRepeatIntervalUnit(DateBuilder.IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60);
    
    CronCalendar cronCal = new CronCalendar("* * 9-12 * * ?"); // exclude 9-12    
    List<Date> fireTimes = TriggerUtils.computeFireTimes(trigger, cronCal, 48);
    Assert.assertEquals(48, fireTimes.size());
    Assert.assertEquals(dateOf(8, 0, 0, 1, 1, 2011), fireTimes.get(0));
    Assert.assertEquals(dateOf(13, 0, 0, 1, 1, 2011), fireTimes.get(1));
    Assert.assertEquals(dateOf(23, 0, 0, 4, 1, 2011), fireTimes.get(47));
  }
