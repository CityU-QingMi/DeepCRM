    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.put("org.quartz.scheduler.instanceName", "TestScheduler");
        props.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        props.put("org.quartz.threadPool.threadCount", "1");
        props.put("org.quartz.scheduler.jmx.export", "true");

        scheduler = new StdSchedulerFactory(props).getScheduler();

        JobDetail jobDetail = newJob(HelloJob.class).withIdentity(JOB_KEY, GROUP_KEY).build();
        Trigger trigger = newTrigger().withIdentity(TRIGGER_KEY, GROUP_KEY).startAt(new Date()).build();

        scheduler.addCalendar(CALENDAR_KEY, new BaseCalendar(), false, false);

        scheduler.scheduleJob(jobDetail, trigger);

        String objectName = QuartzSchedulerResources.generateJMXObjectName(scheduler.getSchedulerName(), scheduler.getSchedulerInstanceId());
        remoteScheduler = new TestRemoteScheduler(objectName);
    }
