    private AsyncAppender(final String name, final Filter filter, final AppenderRef[] appenderRefs,
                          final String errorRef, final int queueSize, final boolean blocking,
                          final boolean ignoreExceptions, final long shutdownTimeout, final Configuration config,
                          final boolean includeLocation, final BlockingQueueFactory<LogEvent> blockingQueueFactory) {
        super(name, filter, null, ignoreExceptions);
        this.queue = blockingQueueFactory.create(queueSize);
        this.queueSize = queueSize;
        this.blocking = blocking;
        this.shutdownTimeout = shutdownTimeout;
        this.config = config;
        this.appenderRefs = appenderRefs;
        this.errorRef = errorRef;
        this.includeLocation = includeLocation;
    }
