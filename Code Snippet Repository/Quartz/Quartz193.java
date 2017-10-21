    protected StdSchedulerFactory getSchedulerFactory(String configFile)
            throws SchedulerException {
        StdSchedulerFactory factory;
        // get Properties
        if (configFile != null) {
            factory = new StdSchedulerFactory(configFile);
        } else {
            factory = new StdSchedulerFactory();
        }
        return factory;
    }
