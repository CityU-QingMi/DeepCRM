    private void tryLogMessage(final String fqcn,
                               final Level level,
                               final Marker marker,
                               final Message msg,
                               final Throwable throwable) {
        try {
            logMessage(fqcn, level, marker, msg, throwable);
        } catch (final Exception e) {
            // LOG4J2-1990 Log4j2 suppresses all exceptions that occur once application called the logger
            handleLogMessageException(e, fqcn, msg);
        }
    }
