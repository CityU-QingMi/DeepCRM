    @Override
    public void append(final LogEvent logEvent) {
        if (!isStarted()) {
            throw new IllegalStateException("AsyncAppender " + getName() + " is not active");
        }
        final Log4jLogEvent memento = Log4jLogEvent.createMemento(logEvent, includeLocation);
        InternalAsyncUtil.makeMessageImmutable(logEvent.getMessage());
        if (!transfer(memento)) {
            if (blocking) {
                // delegate to the event router (which may discard, enqueue and block, or log in current thread)
                final EventRoute route = asyncQueueFullPolicy.getRoute(thread.getId(), memento.getLevel());
                route.logMessage(this, memento);
            } else {
                error("Appender " + getName() + " is unable to write primary appenders. queue is full");
                logToErrorAppenderIfNecessary(false, memento);
            }
        }
    }
