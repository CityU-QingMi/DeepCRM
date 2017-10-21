    public void start(final Configuration config) {
        LOGGER.debug("Starting LoggerContext[name={}, {}] with configuration {}...", getName(), this, config);
        if (configLock.tryLock()) {
            try {
                if (this.isInitialized() || this.isStopped()) {
                    if (this.configuration.isShutdownHookEnabled()) {
                        setUpShutdownHook();
                    }
                    this.setStarted();
                }
            } finally {
                configLock.unlock();
            }
        }
        setConfiguration(config);
        LOGGER.debug("LoggerContext[name={}, {}] started OK with configuration {}.", getName(), this, config);
    }
