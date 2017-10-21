    void enqueueLogMessageInfo(final RingBufferLogEventTranslator translator) {
        // LOG4J2-639: catch NPE if disruptor field was set to null in stop()
        try {
            // Note: we deliberately access the volatile disruptor field afresh here.
            // Avoiding this and using an older reference could result in adding a log event to the disruptor after it
            // was shut down, which could cause the publishEvent method to hang and never return.
            disruptor.publishEvent(translator);
        } catch (final NullPointerException npe) {
            LOGGER.warn("[{}] Ignoring log event after log4j was shut down: {} [{}] {}", contextName,
                    translator.level, translator.loggerName, translator.message.getFormattedMessage()
                            + (translator.thrown == null ? "" : Throwables.toStringList(translator.thrown)));
        }
    }
