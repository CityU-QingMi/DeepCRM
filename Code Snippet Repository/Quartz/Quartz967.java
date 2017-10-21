  @Override
  protected void test(Scheduler scheduler) throws Throwable {
    String pausedGroup = "group";

    scheduler.pauseTriggers(GroupMatcher.triggerGroupEquals(pausedGroup));

    JobDetailImpl jobDetail = new JobDetailImpl("job1", "group", MyJob.class);
    jobDetail.setDurability(true);

    SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger1", pausedGroup);
    trigger.setRepeatInterval(30000);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    trigger.setJobName("job1");
    trigger.setJobGroup("group");

    scheduler.addJob(jobDetail, false);
    scheduler.scheduleJob(trigger);
  }
