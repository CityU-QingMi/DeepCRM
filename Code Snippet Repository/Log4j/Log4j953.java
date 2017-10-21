    public static void setAllLevels(final String parentLogger, final Level level) {
        // 1) get logger config
        // 2) if exact match, use it, if not, create it.
        // 3) set level on logger config
        // 4) update child logger configs with level
        // 5) update loggers
        final LoggerContext loggerContext = LoggerContext.getContext(false);
        final Configuration config = loggerContext.getConfiguration();
        boolean set = setLevel(parentLogger, level, config);
        for (final Map.Entry<String, LoggerConfig> entry : config.getLoggers().entrySet()) {
            if (entry.getKey().startsWith(parentLogger)) {
                set |= setLevel(entry.getValue(), level);
            }
        }
        if (set) {
            loggerContext.updateLoggers();
        }
    }
