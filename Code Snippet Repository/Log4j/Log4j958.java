    @Override
    public void log(final Supplier<LoggerConfig> reconfigured, final String loggerName, final String fqcn,
            final Marker marker, final Level level, final Message data, final Throwable t) {

        final LoggerConfig config = getActiveLoggerConfig(reconfigured);
        try {
            config.log(loggerName, fqcn, marker, level, data, t);
        } finally {
            config.getReliabilityStrategy().afterLogEvent();
        }
    }
