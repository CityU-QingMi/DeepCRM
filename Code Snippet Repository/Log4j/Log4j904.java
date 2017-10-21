    public void setBasicValues(final AsyncLogger anAsyncLogger, final String aLoggerName, final Marker aMarker,
            final String theFqcn, final Level aLevel, final Message msg, final Throwable aThrowable,
            final ContextStack aContextStack, final StackTraceElement aLocation,
            final long aCurrentTimeMillis, final long aNanoTime) {
        this.asyncLogger = anAsyncLogger;
        this.loggerName = aLoggerName;
        this.marker = aMarker;
        this.fqcn = theFqcn;
        this.level = aLevel;
        this.message = msg;
        this.thrown = aThrowable;
        this.contextStack = aContextStack;
        this.location = aLocation;
        this.currentTimeMillis = aCurrentTimeMillis;
        this.nanoTime = aNanoTime;
    }
