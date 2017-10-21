    private boolean hasAsyncLoggers() {
        if (root instanceof AsyncLoggerConfig) {
            return true;
        }
        for (final LoggerConfig logger : loggerConfigs.values()) {
            if (logger instanceof AsyncLoggerConfig) {
                return true;
            }
        }
        return false;
    }
