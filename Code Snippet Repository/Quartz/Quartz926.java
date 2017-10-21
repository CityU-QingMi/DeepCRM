    @Before
    public void setUp() throws SchedulerException {

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory("org/quartz/tests/QTZ225/quartz.properties");
        sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        log.info("------- (Not Scheduling any Jobs - relying on XML definitions --");

        log.info("------- Starting Scheduler ----------------");

        // start the schedule
        sched.start();
    }
