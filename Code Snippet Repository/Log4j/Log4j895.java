    public void setValues(final AsyncLogger anAsyncLogger, final String aLoggerName, final Marker aMarker,
            final String theFqcn, final Level aLevel, final Message msg, final Throwable aThrowable,
            final StringMap mutableContextData, final ContextStack aContextStack, final long threadId,
            final String threadName, final int threadPriority, final StackTraceElement aLocation,
            final long aCurrentTimeMillis, final long aNanoTime) {
        this.threadPriority = threadPriority;
        this.threadId = threadId;
        this.currentTimeMillis = aCurrentTimeMillis;
        this.nanoTime = aNanoTime;
        this.level = aLevel;
        this.threadName = threadName;
        this.loggerName = aLoggerName;
        setMessage(msg);
        this.thrown = aThrowable;
        this.thrownProxy = null;
        this.marker = aMarker;
        this.fqcn = theFqcn;
        this.location = aLocation;
        this.contextData = mutableContextData;
        this.contextStack = aContextStack;
        this.asyncLogger = anAsyncLogger;
    }
