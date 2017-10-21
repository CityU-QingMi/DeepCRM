  @Override
  protected void test(Scheduler sched) throws Throwable {
    Properties props = getSchedulerProps();
    props.setProperty(StdSchedulerFactory.PROP_SCHED_IDLE_WAIT_TIME, "1000");

    sched.start();
    sched.shutdown();

    SchedulerFactory schedFact = new StdSchedulerFactory(props);
    sched = schedFact.getScheduler();
    sched.start();
    sched.shutdown();
  }
