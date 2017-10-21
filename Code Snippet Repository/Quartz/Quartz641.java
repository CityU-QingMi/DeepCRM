    @SuppressWarnings("")
    @Override
    protected void setUp() throws Exception {
        this.fSignaler = new SampleSignaler();
        ClassLoadHelper loadHelper = new CascadingClassLoadHelper();
        loadHelper.initialize();
        this.fJobStore = createJobStore("AbstractJobStoreTest");
        this.fJobStore.initialize(loadHelper, this.fSignaler);
        this.fJobStore.schedulerStarted();

        this.fJobDetail = new JobDetailImpl("job1", "jobGroup1", MyJob.class);
        this.fJobDetail.setDurability(true);
        this.fJobStore.storeJob(this.fJobDetail, false);
    }
