    @Override
    public LogEvent createEvent(final String loggerName, final Marker marker,
                                final String fqcn, final Level level, final Message message,
                                final List<Property> properties, final Throwable t) {
        MutableLogEvent result = mutableLogEventThreadLocal.get();
        if (result == null || result.reserved) {
            final boolean initThreadLocal = result == null;
            result = new MutableLogEvent();

            // usually no need to re-initialize thread-specific fields since the event is stored in a ThreadLocal
            result.setThreadId(Thread.currentThread().getId());
            result.setThreadName(Thread.currentThread().getName()); // Thread.getName() allocates Objects on each call
            result.setThreadPriority(Thread.currentThread().getPriority());
            if (initThreadLocal) {
                mutableLogEventThreadLocal.set(result);
            }
        }
        result.reserved = true;
        result.clear(); // ensure any previously cached values (thrownProxy, source, etc.) are cleared

        result.setLoggerName(loggerName);
        result.setMarker(marker);
        result.setLoggerFqcn(fqcn);
        result.setLevel(level == null ? Level.OFF : level);
        result.setMessage(message);
        result.setThrown(t);
        result.setContextData(injector.injectContextData(properties, (StringMap) result.getContextData()));
        result.setContextStack(ThreadContext.getDepth() == 0 ? ThreadContext.EMPTY_STACK : ThreadContext.cloneStack());// mutable copy
        result.setTimeMillis(message instanceof TimestampMessage
                ? ((TimestampMessage) message).getTimestamp()
                : CLOCK.currentTimeMillis());
        result.setNanoTime(Log4jLogEvent.getNanoClock().nanoTime());

        if (THREAD_NAME_CACHING_STRATEGY == ThreadNameCachingStrategy.UNCACHED) {
            result.setThreadName(Thread.currentThread().getName()); // Thread.getName() allocates Objects on each call
            result.setThreadPriority(Thread.currentThread().getPriority());
        }
        return result;
    }
