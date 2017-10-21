    @Override
    public void start() {
        LOGGER.debug("Starting LoggerContext[name={}, {}]...", getName(), this);
        if (PropertiesUtil.getProperties().getBooleanProperty("log4j.LoggerContext.stacktrace.on.start", false)) {
            LOGGER.debug("Stack trace to locate invoker",
                    new Exception("Not a real error, showing stack trace to locate invoker"));
        }
        if (configLock.tryLock()) {
            try {
                if (this.isInitialized() || this.isStopped()) {
                    this.setStarting();
                    reconfigure();
                    if (this.configuration.isShutdownHookEnabled()) {
                        setUpShutdownHook();
                    }
                    this.setStarted();
                }
            } finally {
                configLock.unlock();
            }
        }
        LOGGER.debug("LoggerContext[name={}, {}] started OK.", getName(), this);
    }
