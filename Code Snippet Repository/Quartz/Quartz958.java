  @Override
  protected void test(Scheduler scheduler) throws Throwable {
    final int ITERATIONS = 25;

    int index = barrier.await();

    for (int cnt = 0; cnt < NUM; cnt++) {
      String jobName = "myJob" + cnt;
      counts.put(jobName, toolkit.getAtomicLong(jobName));
      if (index == 0) {
        System.out.println("Scheduling Job: " + "myJob" + cnt);
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity(jobName, "myJobGroup").build();

        Trigger trigger = TriggerBuilder
            .newTrigger()
            .withIdentity("triggerName" + cnt, "triggerGroup")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
                              .withRepeatCount(ITERATIONS - 1)).build();

        scheduler.scheduleJob(jobDetail, trigger);
      }
    }

    barrier.await();

    scheduler.start();

    if (index == 0) {
      int doneCount = 0;
      while (doneCount != NUM) {
        doneCount = 0;
        ThreadUtil.reallySleep(1000L);

        for (Entry<String, ToolkitAtomicLong> entry : counts.entrySet()) {
          if (entry.getValue().longValue() == ITERATIONS) {
            doneCount++;
          }
          // System.err.println("Entries --" + entry.getKey() + " " + entry.getValue().longValue());
        }

        System.err.println("doneCount: " + doneCount);
      }
    }

    barrier.await();

    scheduler.shutdown();
  }
