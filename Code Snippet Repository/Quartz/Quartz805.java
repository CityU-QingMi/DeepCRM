  public void testValidateTimeOfDayOrder() throws Exception {
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setStartTimeOfDay(new TimeOfDay(12, 0, 0));
    trigger.setEndTimeOfDay(new TimeOfDay(8, 0, 0));
    try {
      trigger.validate();
      fail("Trigger should be invalidate when time of day is not in order.");
    } catch (SchedulerException e) {
      // expected.
    }
  }
