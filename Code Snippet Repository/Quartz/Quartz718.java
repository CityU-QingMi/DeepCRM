    @Override
    protected Scheduler createScheduler(String name, int threadPoolSize) throws SchedulerException {
        try {
            DBConnectionManager.getInstance().addConnectionProvider(name, new FlakyConnectionProvider(name));
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
        JobStoreTX jobStore = new JobStoreTX();
        jobStore.setDataSource(name);
        jobStore.setTablePrefix("QRTZ_");
        jobStore.setInstanceId("AUTO");
        jobStore.setDbRetryInterval(50);
        DirectSchedulerFactory.getInstance().createScheduler(name + "Scheduler", "AUTO", new SimpleThreadPool(threadPoolSize, Thread.NORM_PRIORITY), jobStore, null, 0, -1, 50);
        return SchedulerRepository.getInstance().lookup(name + "Scheduler");
    }
