  protected void test(Scheduler scheduler) throws Throwable {
    JobDetailImpl jobDetail = new JobDetailImpl("recoveryjob", null, RecoveryTestJob.class);
    jobDetail.setDurability(true);
    jobDetail.setRequestsRecovery(true);

    SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger1", "group");
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    trigger.setRepeatInterval(1000L * 60L * 60L * 24L * 7L);
    trigger.setJobName("recoveryjob");
    trigger.getJobDataMap().putAsString(RecoveryTest.class.getName(), true);

    scheduler.addJob(jobDetail, false);
    scheduler.scheduleJob(trigger);

    localBarrier.await();
    pass();
    Runtime.getRuntime().halt(0);
  }
