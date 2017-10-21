    public void initializeBuilder(final Log4jLogEvent.Builder builder) {
        builder.setContextData(contextData) //
                .setContextStack(contextStack) //
                .setEndOfBatch(endOfBatch) //
                .setIncludeLocation(includeLocation) //
                .setLevel(getLevel()) // ensure non-null
                .setLoggerFqcn(fqcn) //
                .setLoggerName(loggerName) //
                .setMarker(marker) //
                .setMessage(getNonNullImmutableMessage()) // ensure non-null & immutable
                .setNanoTime(nanoTime) //
                .setSource(location) //
                .setThreadId(threadId) //
                .setThreadName(threadName) //
                .setThreadPriority(threadPriority) //
                .setThrown(getThrown()) // may deserialize from thrownProxy
                .setThrownProxy(thrownProxy) // avoid unnecessarily creating thrownProxy
                .setTimeMillis(currentTimeMillis);
    }
