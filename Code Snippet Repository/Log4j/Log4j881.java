    public boolean tryPublish(final RingBufferLogEventTranslator translator) {
        // LOG4J2-639: catch NPE if disruptor field was set to null in stop()
        try {
            return disruptor.getRingBuffer().tryPublishEvent(translator);
        } catch (final NullPointerException npe) {
            LOGGER.warn("[{}] Ignoring log event after log4j was shut down: {} [{}] {}", contextName,
                    translator.level, translator.loggerName, translator.message.getFormattedMessage()
                            + (translator.thrown == null ? "" : Throwables.toStringList(translator.thrown)));
            return false;
        }
    }
