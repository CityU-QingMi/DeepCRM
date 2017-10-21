    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        super.stop(timeout, timeUnit, false);
        LOGGER.trace("AsyncAppender stopping. Queue still has {} events.", queue.size());
        thread.shutdown();
        try {
            thread.join(shutdownTimeout);
        } catch (final InterruptedException ex) {
            LOGGER.warn("Interrupted while stopping AsyncAppender {}", getName());
        }
        LOGGER.trace("AsyncAppender stopped. Queue has {} events.", queue.size());

        if (DiscardingAsyncQueueFullPolicy.getDiscardCount(asyncQueueFullPolicy) > 0) {
            LOGGER.trace("AsyncAppender: {} discarded {} events.", asyncQueueFullPolicy,
                DiscardingAsyncQueueFullPolicy.getDiscardCount(asyncQueueFullPolicy));
        }
        setStopped();
        return true;
    }
