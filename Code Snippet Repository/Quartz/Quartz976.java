  private Scheduler setupScheduler() throws IOException, SchedulerException {
    Properties props = new Properties();
    props.load(ShutdownClient.class.getResourceAsStream("/org/quartz/quartz.properties"));
    props.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, TerracottaJobStore.class.getName());
    props.setProperty(AbstractTerracottaJobStore.TC_CONFIGURL_PROP, getTerracottaUrl());
    props.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_ID, StdSchedulerFactory.AUTO_GENERATE_INSTANCE_ID);

    SchedulerFactory schedFact = new StdSchedulerFactory(props);
    Scheduler sched = schedFact.getScheduler();
    sched.start();
    return sched;
  }
