  public void testGeneratorThroughSchedulerInstatiation() throws Exception {
    try {
      JdbcQuartzTestUtilities.createDatabase("MeSchedulerDatabase");
    } catch (SQLException e) {
      throw new AssertionError(e);
    }

    Properties config = new Properties();
    config.setProperty("org.quartz.scheduler.instanceName", "MeScheduler");
    config.setProperty("org.quartz.scheduler.instanceId", "AUTO");
    config.setProperty("org.quartz.scheduler.instanceIdGenerator.class", 
        org.quartz.simpl.SystemPropertyInstanceIdGenerator.class.getName());
    config.setProperty("org.quartz.scheduler.instanceIdGenerator.prepend", "1");
    config.setProperty("org.quartz.scheduler.instanceIdGenerator.postpend", "2");
    config.setProperty("org.quartz.scheduler.instanceIdGenerator.systemPropertyName", "blah.blah");
    config.setProperty("org.quartz.threadPool.threadCount", "1");
    config.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
    config.setProperty("org.quartz.jobStore.class", org.quartz.impl.jdbcjobstore.JobStoreTX.class.getName());
    config.setProperty("org.quartz.jobStore.isClustered", "true");
    config.setProperty("org.quartz.jobStore.dataSource", "MeSchedulerDatabase");
    
    Scheduler sched = new StdSchedulerFactory(config).getScheduler();    
    
    assertEquals("1goo2", sched.getSchedulerInstanceId());
  }
