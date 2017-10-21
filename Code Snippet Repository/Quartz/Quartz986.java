  @Override
  public void addSchedulerProperties(Properties props) {
    // these props might generate warning messages, but that is on purpose, they are here to make sure we don't throw
    // an exception (read: please don't remove)

    // Note by HUNG: if I don't comment these out, after porting from dso to express mode, this test will fail
    // props.setProperty("org.quartz.jobStore.tcConfig", "blah blah blah");
    // props.setProperty("org.quartz.jobStore.tcConfigUrl", "blah blah blah");

    // set synch write to make sure it doesn't blow up
    props.setProperty("org.quartz.jobStore.synchronousWrite", "false");

    // set AUTO instance ID (just to make sure it doesn't blow up)
    props.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_ID, StdSchedulerFactory.AUTO_GENERATE_INSTANCE_ID);

    props.setProperty("org.quartz.threadPool.threadCount", "1");
    props.setProperty("org.quartz.jobStore.class", TerracottaJobStore.class.getName());
    props.setProperty(StdSchedulerFactory.PROP_SCHED_IDLE_WAIT_TIME, "1000");
  }
