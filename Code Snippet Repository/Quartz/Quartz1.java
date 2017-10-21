  public void run() throws Exception {
    Logger log = LoggerFactory.getLogger(PlugInExample.class);

    // First we must get a reference to a scheduler
    SchedulerFactory sf = new StdSchedulerFactory();
    Scheduler sched = null;
    try {
      sched = sf.getScheduler();
    } catch (NoClassDefFoundError e) {
      log.error(" Unable to load a class - most likely you do not have jta.jar on the classpath. If not present in the examples/lib folder, please " +
                "add it there for this sample to run.", e);
      return;
    }

    log.info("------- Initialization Complete -----------");

    log.info("------- (Not Scheduling any Jobs - relying on XML definitions --");

    log.info("------- Starting Scheduler ----------------");

    // start the schedule
    sched.start();

    log.info("------- Started Scheduler -----------------");

    log.info("------- Waiting five minutes... -----------");

    // wait five minutes to give our jobs a chance to run
    try {
      Thread.sleep(300L * 1000L);
    } catch (Exception e) {
      //
    }

    // shut down the scheduler
    log.info("------- Shutting Down ---------------------");
    sched.shutdown(true);
    log.info("------- Shutdown Complete -----------------");

    SchedulerMetaData metaData = sched.getMetaData();
    log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
  }
