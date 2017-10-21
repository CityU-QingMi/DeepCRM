  @Override
  protected void test(Scheduler scheduler) throws Throwable {
    JobDetailImpl jobDetail = new JobDetailImpl("testjob", null, SimpleJob.class);
    jobDetail.setDurability(true);

    SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger1", "group");
    trigger.setRepeatInterval(30000);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    trigger.setJobName("testjob");
    trigger.setCalendarName("mycal");

    // This calendar doesn't do anything really, just testing that calendars work
    scheduler.addCalendar("mycal", new BaseCalendar(), false, true);
    scheduler.addJob(jobDetail, false);
    scheduler.scheduleJob(trigger);

    SimpleJob.localBarrier.await();
  }
