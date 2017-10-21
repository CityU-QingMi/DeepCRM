  protected Scheduler setupScheduler() throws IOException, SchedulerException {
    props.load(getClass().getResourceAsStream("/org/quartz/quartz.properties"));
    props.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, TerracottaJobStore.class.getName());
    props.setProperty(AbstractTerracottaJobStore.TC_CONFIGURL_PROP, getTerracottaUrl());
    props.setProperty("org.quartz.jobStore.synchronousWrite", String.valueOf(isSynchWrite()));
    props.setProperty("org.quartz.jobStore.estimatedTimeToReleaseAndAcquireTrigger", "10");
    props.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_ID, StdSchedulerFactory.AUTO_GENERATE_INSTANCE_ID);

    addSchedulerProperties(props);
    
    System.out.println(props);
    SchedulerFactory schedFact = new StdSchedulerFactory(props);
    Scheduler sched = schedFact.getScheduler();
    if (isStartingScheduler()) {
      sched.start();
    }

    return sched;
  }
