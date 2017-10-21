    public static void setLevel(final Map<String, Level> levelMap) {
        final LoggerContext loggerContext = LoggerContext.getContext(false);
        final Configuration config = loggerContext.getConfiguration();
        boolean set = false;
        for (final Map.Entry<String, Level> entry : levelMap.entrySet()) {
            final String loggerName = entry.getKey();
            final Level level = entry.getValue();
            set |= setLevel(loggerName, level, config);
        }
        if (set) {
            loggerContext.updateLoggers();
        }
    }
