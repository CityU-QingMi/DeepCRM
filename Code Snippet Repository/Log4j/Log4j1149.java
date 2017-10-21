    private Log4jLogEvent(final String loggerName, final Marker marker, final String loggerFQCN, final Level level,
            final Message message, final Throwable thrown, final ThrowableProxy thrownProxy,
            final StringMap contextData, final ThreadContext.ContextStack contextStack, final long threadId,
            final String threadName, final int threadPriority, final StackTraceElement source,
            final long timestampMillis, final long nanoTime) {
        this.loggerName = loggerName;
        this.marker = marker;
        this.loggerFqcn = loggerFQCN;
        this.level = level == null ? Level.OFF : level; // LOG4J2-462, LOG4J2-465
        this.message = message;
        this.thrown = thrown;
        this.thrownProxy = thrownProxy;
        this.contextData = contextData == null ? ContextDataFactory.createContextData() : contextData;
        this.contextStack = contextStack == null ? ThreadContext.EMPTY_STACK : contextStack;
        this.timeMillis = message instanceof TimestampMessage
                ? ((TimestampMessage) message).getTimestamp()
                : timestampMillis;
        this.threadId = threadId;
        this.threadName = threadName;
        this.threadPriority = threadPriority;
        this.source = source;
        if (message != null && message instanceof LoggerNameAwareMessage) {
            ((LoggerNameAwareMessage) message).setLoggerName(loggerName);
        }
        this.nanoTime = nanoTime;
    }
