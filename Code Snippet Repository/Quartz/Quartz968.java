  @Override
  public void addSchedulerProperties(Properties properties) {
    properties.setProperty(StdSchedulerFactory.PROP_SCHED_IDLE_WAIT_TIME, "1000");

    try {
      index.set(barrier.await());
    } catch (Exception e) {
      throw new AssertionError(e);
    }

    properties.setProperty("org.quartz.threadPool.threadCount", index.get() == 0 ? "1" : "10");
  }
