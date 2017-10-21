    @Override
    public void enqueueEvent(final LogEvent event, final AsyncLoggerConfig asyncLoggerConfig) {
        // LOG4J2-639: catch NPE if disruptor field was set to null after our check above
        try {
            final LogEvent logEvent = prepareEvent(event);
            enqueue(logEvent, asyncLoggerConfig);
        } catch (final NullPointerException npe) {
            // Note: NPE prevents us from adding a log event to the disruptor after it was shut down,
            // which could cause the publishEvent method to hang and never return.
            LOGGER.warn("Ignoring log event after log4j was shut down: {} [{}] {}", event.getLevel(),
                    event.getLoggerName(), event.getMessage().getFormattedMessage()
                            + (event.getThrown() == null ? "" : Throwables.toStringList(event.getThrown())));
        }
    }
