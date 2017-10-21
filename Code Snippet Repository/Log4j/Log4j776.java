    @Override
    public void initialize(final RollingFileManager aManager) {
        this.manager = aManager;
        final Date now = new Date();
        final Date lastRollForFile = cronExpression.getPrevFireTime(new Date(this.manager.getFileTime()));
        final Date lastRegularRoll = cronExpression.getPrevFireTime(new Date());
        aManager.getPatternProcessor().setCurrentFileTime(lastRegularRoll.getTime());
        LOGGER.debug("LastRollForFile {}, LastRegularRole {}", lastRollForFile, lastRegularRoll);
        aManager.getPatternProcessor().setPrevFileTime(lastRegularRoll.getTime());
        if (checkOnStartup && lastRollForFile != null && lastRegularRoll != null &&
                lastRollForFile.before(lastRegularRoll)) {
            lastRollDate = lastRollForFile;
            rollover();
        }

        final ConfigurationScheduler scheduler = configuration.getScheduler();
        if (!scheduler.isExecutorServiceSet()) {
            // make sure we have a thread pool
            scheduler.incrementScheduledItems();
        }
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        lastRollDate = lastRegularRoll;
        future = scheduler.scheduleWithCron(cronExpression, now, new CronTrigger());
        LOGGER.debug(scheduler.toString());
    }
