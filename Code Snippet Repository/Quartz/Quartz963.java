  @Override
  protected void test(Scheduler sched) throws Throwable {
    int index = barrier.await();

    int triggers = 1000;
    long now = System.currentTimeMillis() + 5000L;
    if (index == 0) {
      sched.start();
      barrier.await();

      final int duration = 90;
      while (counter.get() < triggers
             && System.currentTimeMillis() < now + (triggers / 10 * 500) + TimeUnit.SECONDS.toMillis(duration)) {
        Thread.sleep(1500);
        System.out.println(new Date() + " - Waiting on another " + (triggers - counter.get()) + " triggers to fire");
      }

      Assert.assertEquals("All " + triggers + " triggers should have fired by now (" + new Date() + ")", triggers,
                          counter.get());

    } else {
      barrier.await();

      JobDetail jobDetail = newJob(MyJob.class).withIdentity("testJob").storeDurably(true).build();

      sched.addJob(jobDetail, false);

      for (int i = 0; i < triggers; i++) {
        Trigger trigger = newTrigger().forJob("testJob").withIdentity("trigger" + i, "group")
            .startAt(new Date(now + ((i / (triggers / 10)) + 1) * 500))
            .withSchedule(simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
        sched.scheduleJob(trigger);
      }
    }

    if (index == 0) {
      sched.shutdown();
    }
  }
