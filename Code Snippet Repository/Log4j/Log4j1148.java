    @Deprecated
    public static Log4jLogEvent createEvent(final String loggerName, final Marker marker, final String loggerFQCN,
                                            final Level level, final Message message, final Throwable thrown,
                                            final ThrowableProxy thrownProxy,
                                            final Map<String, String> mdc, final ThreadContext.ContextStack ndc,
                                            final String threadName, final StackTraceElement location,
                                            final long timestamp) {
        final Log4jLogEvent result = new Log4jLogEvent(loggerName, marker, loggerFQCN, level, message, thrown,
                thrownProxy, createContextData(mdc), ndc, 0, threadName, 0, location, timestamp, nanoClock.nanoTime());
        return result;
    }
