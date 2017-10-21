    @Override
    public void beforeStopConfiguration(final Configuration configuration) {
        // only sleep once per configuration stop
        if (loggerConfig == configuration.getRootLogger()) {
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (final InterruptedException e) {
                StatusLogger.getLogger().warn("Sleep before stop configuration was interrupted.");
            }
        }
    }
