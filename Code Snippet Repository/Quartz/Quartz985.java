  @Override
  protected void test(final Scheduler scheduler) throws Throwable {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("Running shutdown hook");
        try {
          scheduler.shutdown(true);
          System.err.println("Scheduler shutdown completed normally - signalling success");
          pass();
        } catch (Throwable t) {
          System.err.println("Scheduler shutdown completed abnormally - halting JVM");
          t.printStackTrace();
          Runtime.getRuntime().halt(-1);
        }
      }
    });

    JobDetail jobDetail = new JobDetailImpl("job", ShutdownHookJob.class);
    Trigger trigger = new SimpleTriggerImpl("trigger", SimpleTrigger.REPEAT_INDEFINITELY, 10L);
    scheduler.scheduleJob(jobDetail, trigger);

    Thread.sleep(3000L);

    System.err.println("Starting VM shutdown, the shutdown hook will handle the test from here.");
    Runtime.getRuntime().exit(0);
    Assert.fail();
  }
