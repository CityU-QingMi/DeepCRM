  @Override
  protected void test(Scheduler sched) throws Throwable {
    int index = barrier.await();

    if (index == 0) {
      JobDetailImpl jobDetail = new JobDetailImpl("testjob", null, TestJob.class);
      jobDetail.setDurability(true);

      long when = System.currentTimeMillis() + 5000L;

      SimpleTriggerImpl trigger1 = new SimpleTriggerImpl("trigger1", "group", new Date(when));
      trigger1.setJobName("testjob");
      SimpleTriggerImpl trigger2 = new SimpleTriggerImpl("trigger2", "group", new Date(when));
      trigger2.setJobName("testjob");

      sched.addJob(jobDetail, false);

      sched.scheduleJob(trigger1);
      sched.scheduleJob(trigger2);
    }

    localBarrier.await();
  }
