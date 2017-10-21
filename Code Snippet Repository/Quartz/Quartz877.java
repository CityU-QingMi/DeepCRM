    @Override
    public void createService() throws Exception {
        log.info("Create QuartzService(" + jndiName + ")...");

        if (error) {
            log
                    .error("Must specify only one of 'Properties' or 'PropertiesFile'");

            throw new Exception(
                    "Must specify only one of 'Properties' or 'PropertiesFile'");
        }

        schedulerFactory = new StdSchedulerFactory();

        try {
            if (useProperties) {
                schedulerFactory.initialize(properties);
            }

            if (usePropertiesFile) {
                schedulerFactory.initialize(propertiesFile);
            }
        } catch (Exception e) {
            log.error("Failed to initialize Scheduler", e);

            throw new SchedulerConfigException(
                    "Failed to initialize Scheduler - ", e);
        }

        log.info("QuartzService(" + jndiName + ") created.");
    }
