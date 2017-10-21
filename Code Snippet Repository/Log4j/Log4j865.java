    @Override
    public void translateTo(final RingBufferLogEvent event, final long sequence, final Object... args) {
        // Implementation note: candidate for optimization: exceeds 35 bytecodes.
        final AsyncLogger asyncLogger = (AsyncLogger) args[0];
        final StackTraceElement location = (StackTraceElement) args[1];
        final String fqcn = (String) args[2];
        final Level level = (Level) args[3];
        final Marker marker = (Marker) args[4];
        final Message message = (Message) args[5];
        final Throwable thrown = (Throwable) args[6];

        // needs shallow copy to be fast (LOG4J2-154)
        final ContextStack contextStack = ThreadContext.getImmutableStack();

        final Thread currentThread = Thread.currentThread();
        final String threadName = THREAD_NAME_CACHING_STRATEGY.getThreadName();
        event.setValues(asyncLogger, asyncLogger.getName(), marker, fqcn, level, message, thrown,
                // config properties are taken care of in the EventHandler thread
                // in the AsyncLogger#actualAsyncLog method
                CONTEXT_DATA_INJECTOR.injectContextData(null, (StringMap) event.getContextData()),
                contextStack, currentThread.getId(), threadName, currentThread.getPriority(), location,
                CLOCK.currentTimeMillis(), nanoClock.nanoTime());
    }
