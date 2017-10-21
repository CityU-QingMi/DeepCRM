  @Override
  public void doTest() throws Throwable {
    Scheduler scheduler = null;
    try {
      scheduler = setupScheduler();
      test(scheduler);
    } finally {
      if (scheduler != null && !scheduler.isShutdown()) {
        scheduler.shutdown();
      }
    }
  }
