    @Override
    protected Scheduler createScheduler(String name, int threadPoolSize) throws SchedulerException {
        try {
            JdbcQuartzTestUtilities.createDatabase(name + "Database");
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
        JobStoreTX jobStore = new JobStoreTX();
        jobStore.setDataSource(name + "Database");
        jobStore.setTablePrefix("QRTZ_");
        jobStore.setInstanceId("AUTO");
        DirectSchedulerFactory.getInstance().createScheduler(name + "Scheduler", "AUTO", new SimpleThreadPool(threadPoolSize, Thread.NORM_PRIORITY), jobStore);
        return SchedulerRepository.getInstance().lookup(name + "Scheduler");
    }
