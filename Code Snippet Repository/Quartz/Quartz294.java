    public void initialize(ClassLoadHelper loadHelper,
            SchedulerSignaler signaler) throws SchedulerConfigException {

        if (dsName == null) { 
            throw new SchedulerConfigException("DataSource name not set."); 
        }

        classLoadHelper = loadHelper;
        if(isThreadsInheritInitializersClassLoadContext()) {
            log.info("JDBCJobStore threads will inherit ContextClassLoader of thread: " + Thread.currentThread().getName());
            initializersLoader = Thread.currentThread().getContextClassLoader();
        }
        
        this.schedSignaler = signaler;

        // If the user hasn't specified an explicit lock handler, then 
        // choose one based on CMT/Clustered/UseDBLocks.
        if (getLockHandler() == null) {
            
            // If the user hasn't specified an explicit lock handler, 
            // then we *must* use DB locks with clustering
            if (isClustered()) {
                setUseDBLocks(true);
            }
            
            if (getUseDBLocks()) {
                if(getDriverDelegateClass() != null && getDriverDelegateClass().equals(MSSQLDelegate.class.getName())) {
                    if(getSelectWithLockSQL() == null) {
                        String msSqlDflt = "SELECT * FROM {0}LOCKS WITH (UPDLOCK,ROWLOCK) WHERE " + COL_SCHEDULER_NAME + " = {1} AND LOCK_NAME = ?";
                        getLog().info("Detected usage of MSSQLDelegate class - defaulting 'selectWithLockSQL' to '" + msSqlDflt + "'.");
                        setSelectWithLockSQL(msSqlDflt);
                    }
                }
                getLog().info("Using db table-based data access locking (synchronization).");
                setLockHandler(new StdRowLockSemaphore(getTablePrefix(), getInstanceName(), getSelectWithLockSQL()));
            } else {
                getLog().info(
                    "Using thread monitor-based data access locking (synchronization).");
                setLockHandler(new SimpleSemaphore());
            }
        }

    }
