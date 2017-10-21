    @PluginFactory
    public static Loggers createLoggers(@PluginElement("Loggers") final LoggerConfig[] loggers) {
        final ConcurrentMap<String, LoggerConfig> loggerMap = new ConcurrentHashMap<>();
        LoggerConfig root = null;

        for (final LoggerConfig logger : loggers) {
            if (logger != null) {
                if (logger.getName().isEmpty()) {
                    if (root != null) {
                        throw new IllegalStateException("Configuration has multiple root loggers. There can be only one.");
                    }
                    root = logger;
                }
                loggerMap.put(logger.getName(), logger);
            }
        }

        return new Loggers(loggerMap, root);
    }
