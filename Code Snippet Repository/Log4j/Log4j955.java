    public static void setLevel(final String loggerName, final Level level) {
        final LoggerContext loggerContext = LoggerContext.getContext(false);
        if (Strings.isEmpty(loggerName)) {
            setRootLevel(level);
        } else {
            if (setLevel(loggerName, level, loggerContext.getConfiguration())) {
                loggerContext.updateLoggers();
            }
        }
    }
