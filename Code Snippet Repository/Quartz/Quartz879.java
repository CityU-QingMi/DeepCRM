    @Override
    public void stopService() throws Exception {
        log.info("Stop QuartzService(" + jndiName + ")...");

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            scheduler.shutdown();
        } catch (Exception e) {
            log.error("Failed to shutdown Scheduler", e);

            throw new SchedulerConfigException(
                    "Failed to shutdown Scheduler - ", e);
        }

        unbind(jndiName);

        log.info("QuartzService(" + jndiName + ") stopped.");
    }
