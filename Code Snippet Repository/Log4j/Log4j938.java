    private ScheduledExecutorService getExecutorService() {
        if (executorService == null) {
            if (scheduledItems > 0) {
                LOGGER.debug("{} starting {} threads", name, scheduledItems);
                scheduledItems = Math.min(scheduledItems, MAX_SCHEDULED_ITEMS);
                final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(scheduledItems,
                        Log4jThreadFactory.createDaemonThreadFactory("Scheduled"));
                executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
                executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
                this.executorService = executor;

            } else {
                LOGGER.debug("{}: No scheduled items", name);
            }
        }
        return executorService;
    }
