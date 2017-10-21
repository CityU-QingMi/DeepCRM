  @Test
  public void testJobStorage() throws Exception {
    ToolkitInternal mock = mockToolkitFor("mocked-not-clustered");

    Properties props = new Properties();
    props.load(getClass().getResourceAsStream("/org/quartz/quartz.properties"));
    props.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, TerracottaJobStore.class.getName());
    props.setProperty(AbstractTerracottaJobStore.TC_CONFIGURL_PROP, "mocked-not-clustered");

    SchedulerFactory schedFact = new StdSchedulerFactory(props);
    Scheduler scheduler = schedFact.getScheduler();
    try {
      scheduler.start();
      
      JobDetail jobDetail = JobBuilder.newJob(SomeJob.class).withIdentity("testjob", "testjobgroup").storeDurably().build();
      scheduler.addJob(jobDetail, false);
      
      Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10)).build();
      scheduler.scheduleJob(trigger);
    } finally {
      scheduler.shutdown();
    }

    verify(mock).getStore(eq("_tc_quartz_jobs|DefaultQuartzScheduler"),
            refEq(new ToolkitStoreConfigBuilder().consistency(ToolkitConfigFields.Consistency.STRONG).concurrency(1).build()),
            isNull(Class.class));
    verify(mock).getStore(eq("_tc_quartz_triggers|DefaultQuartzScheduler"),
            refEq(new ToolkitStoreConfigBuilder().consistency(ToolkitConfigFields.Consistency.STRONG).concurrency(1).build()),
            isNull(Class.class));
    verify(mock).getStore(eq("_tc_quartz_fired_trigger|DefaultQuartzScheduler"),
            refEq(new ToolkitStoreConfigBuilder().consistency(ToolkitConfigFields.Consistency.STRONG).concurrency(1).build()),
            isNull(Class.class));
    verify(mock).getStore(eq("_tc_quartz_calendar_wrapper|DefaultQuartzScheduler"),
            refEq(new ToolkitStoreConfigBuilder().consistency(ToolkitConfigFields.Consistency.STRONG).concurrency(1).build()),
            isNull(Class.class));
    
    verify(mock).getSet("_tc_quartz_grp_names|DefaultQuartzScheduler", String.class);
    verify(mock).getSet("_tc_quartz_grp_paused_names|DefaultQuartzScheduler", String.class);
    verify(mock).getSet("_tc_quartz_blocked_jobs|DefaultQuartzScheduler", JobKey.class);
    verify(mock).getSet("_tc_quartz_grp_names_triggers|DefaultQuartzScheduler", String.class);
    verify(mock).getSet("_tc_quartz_grp_paused_trogger_names|DefaultQuartzScheduler", String.class);
    verify(mock).getSet("_tc_quartz_grp_jobs_testjobgroup|DefaultQuartzScheduler", String.class);
    verify(mock).getSet("_tc_quartz_grp_triggers_DEFAULT|DefaultQuartzScheduler", String.class);

    verify(mock).getSortedSet("_tc_time_trigger_sorted_set|DefaultQuartzScheduler", TimeTrigger.class);
    verify(mock).shutdown();
    
    allowNonPersistentInteractions(mock);
    verifyNoMoreInteractions(mock);
  }
