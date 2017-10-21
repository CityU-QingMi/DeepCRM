    @Override
    public void initialize(ClassLoadHelper loadHelper,
            SchedulerSignaler signaler) throws SchedulerConfigException {

        if (nonManagedTxDsName == null) {
            throw new SchedulerConfigException(
                "Non-ManagedTX DataSource name not set!  " +
                "If your 'org.quartz.jobStore.dataSource' is XA, then set " + 
                "'org.quartz.jobStore.nonManagedTXDataSource' to a non-XA "+ 
                "datasource (for the same DB).  " + 
                "Otherwise, you can set them to be the same.");
        }

        if (getLockHandler() == null) {
            // If the user hasn't specified an explicit lock handler, 
            // then we *must* use DB locks with CMT...
            setUseDBLocks(true);
        }

        super.initialize(loadHelper, signaler);

        getLog().info("JobStoreCMT initialized.");
    }
