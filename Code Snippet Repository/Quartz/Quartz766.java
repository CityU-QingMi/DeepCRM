    @Before
    public void beforeTest() throws Exception {
        resetDatabaseData();
        JobStoreTX jobStore = new JobStoreTX();
        jobStore.setDataSource(DB_NAME);
        jobStore.setTablePrefix("QRTZ_");
        jobStore.setInstanceId("AUTO");
        DirectSchedulerFactory.getInstance().createScheduler(SCHEDULER_NAME, "AUTO", new SimpleThreadPool(4, Thread.NORM_PRIORITY), jobStore);
        scheduler = SchedulerRepository.getInstance().lookup(SCHEDULER_NAME);
        //scheduler.start(); // Do not start scheduler to produce the defect case.
    }
