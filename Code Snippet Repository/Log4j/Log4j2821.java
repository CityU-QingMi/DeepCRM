    private static LogEvent createLogEvent(final Message message) {
        final Marker marker = null;
        final String fqcn = "com.mycom.myproject.mypackage.MyClass";
        final org.apache.logging.log4j.Level level = org.apache.logging.log4j.Level.DEBUG;
        final Throwable t = null;
        final Map<String, String> mdc = null;
        final ThreadContext.ContextStack ndc = null;
        final String threadName = null;
        final StackTraceElement location = null;
        final long timestamp = 12345678;

        return Log4jLogEvent.newBuilder() //
            .setLoggerName("name(ignored)") //
            .setMarker(marker) //
            .setLoggerFqcn(fqcn) //
            .setLevel(level) //
            .setMessage(message) //
            .setThrown(t) //
            .setContextMap(mdc) //
            .setContextStack(ndc) //
            .setThreadName(threadName) //
            .setSource(location) //
            .setTimeMillis(timestamp) //
            .build();
    }
