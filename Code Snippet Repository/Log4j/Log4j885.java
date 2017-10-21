    @Override
    public EventRoute getRoute(final long backgroundThreadId, final Level level) {
        if (level.isLessSpecificThan(thresholdLevel)) {
            if (discardCount.getAndIncrement() == 0) {
                LOGGER.warn("Async queue is full, discarding event with level {}. " +
                        "This message will only appear once; future events from {} " +
                        "are silently discarded until queue capacity becomes available.",
                        level, thresholdLevel);
            }
            return EventRoute.DISCARD;
        }
        return super.getRoute(backgroundThreadId, level);
    }
