    @Deprecated
    public static AsyncAppender createAppender(final AppenderRef[] appenderRefs, final String errorRef,
                                               final boolean blocking, final long shutdownTimeout, final int size,
                                               final String name, final boolean includeLocation, final Filter filter,
                                               final Configuration config, final boolean ignoreExceptions) {
        if (name == null) {
            LOGGER.error("No name provided for AsyncAppender");
            return null;
        }
        if (appenderRefs == null) {
            LOGGER.error("No appender references provided to AsyncAppender {}", name);
        }

        return new AsyncAppender(name, filter, appenderRefs, errorRef, size, blocking, ignoreExceptions,
            shutdownTimeout, config, includeLocation, new ArrayBlockingQueueFactory<LogEvent>());
    }
