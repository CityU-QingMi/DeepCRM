  public void testValidateInterval() throws Exception {
    DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
    trigger.setName("test");
    trigger.setGroup("test");
    trigger.setJobKey(JobKey.jobKey("test"));
    
    trigger.setRepeatIntervalUnit(IntervalUnit.HOUR);
    trigger.setRepeatInterval(25);
    try {
      trigger.validate();
      fail("Trigger should be invalidate when interval is greater than 24 hours.");
    } catch (SchedulerException e) {
      // expected.
    }
    
    trigger.setRepeatIntervalUnit(IntervalUnit.MINUTE);
    trigger.setRepeatInterval(60 * 25);
    try {
      trigger.validate();
      fail("Trigger should be invalidate when interval is greater than 24 hours.");
    } catch (SchedulerException e) {
      // expected.
    }

    trigger.setRepeatIntervalUnit(IntervalUnit.SECOND);
    trigger.setRepeatInterval(60 * 60 * 25);
    try {
      trigger.validate();
      fail("Trigger should be invalidate when interval is greater than 24 hours.");
    } catch (SchedulerException e) {
      // expected.
    }
    
    try {
      trigger.setRepeatIntervalUnit(IntervalUnit.DAY);
      trigger.validate();
      fail("Trigger should be invalidate when interval unit > HOUR.");
    } catch (Exception e) {
      // expected.
    }

    try {
      trigger.setRepeatIntervalUnit(IntervalUnit.SECOND);
      trigger.setRepeatInterval(0);
      trigger.validate();
      fail("Trigger should be invalidate when interval is zero.");
    } catch (Exception e) {
      // expected.
    }
  }
