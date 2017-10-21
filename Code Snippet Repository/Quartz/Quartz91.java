    public QuartzScheduler(QuartzSchedulerResources resources, long idleWaitTime, @Deprecated long dbRetryInterval)
        throws SchedulerException {
        this.resources = resources;
        if (resources.getJobStore() instanceof JobListener) {
            addInternalJobListener((JobListener)resources.getJobStore());
        }

        this.schedThread = new QuartzSchedulerThread(this, resources);
        ThreadExecutor schedThreadExecutor = resources.getThreadExecutor();
        schedThreadExecutor.execute(this.schedThread);
        if (idleWaitTime > 0) {
            this.schedThread.setIdleWaitTime(idleWaitTime);
        }

        jobMgr = new ExecutingJobsManager();
        addInternalJobListener(jobMgr);
        errLogger = new ErrorLogger();
        addInternalSchedulerListener(errLogger);

        signaler = new SchedulerSignalerImpl(this, this.schedThread);
        
        getLog().info("Quartz Scheduler v." + getVersion() + " created.");
    }
