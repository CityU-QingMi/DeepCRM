    private void logWithVarargTranslator(final String fqcn, final Level level, final Marker marker,
            final Message message, final Throwable thrown) {
        // Implementation note: candidate for optimization: exceeds 35 bytecodes.

        final Disruptor<RingBufferLogEvent> disruptor = loggerDisruptor.getDisruptor();
        if (disruptor == null) {
            LOGGER.error("Ignoring log event after Log4j has been shut down.");
            return;
        }
        // if the Message instance is reused, there is no point in freezing its message here
        if (!isReused(message)) {
            InternalAsyncUtil.makeMessageImmutable(message);
        }
        // calls the translateTo method on this AsyncLogger
        disruptor.getRingBuffer().publishEvent(this, this, calcLocationIfRequested(fqcn), fqcn, level, marker, message,
                thrown);
    }
