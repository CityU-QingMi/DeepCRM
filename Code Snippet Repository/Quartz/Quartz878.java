    @Override
    public void startService() throws Exception {
        log.info("Start QuartzService(" + jndiName + ")...");

        try {
            rebind();
        } catch (NamingException ne) {
            log.error("Failed to rebind Scheduler", ne);

            throw new SchedulerConfigException("Failed to rebind Scheduler - ",
                    ne);
        }

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            if (startScheduler) {
                scheduler.start();
            } else {
                log.info("Skipping starting the scheduler (will not run jobs).");
            }
        } catch (Exception e) {
            log.error("Failed to start Scheduler", e);

            throw new SchedulerConfigException("Failed to start Scheduler - ",
                    e);
        }

        log.info("QuartzService(" + jndiName + ") started.");
    }
