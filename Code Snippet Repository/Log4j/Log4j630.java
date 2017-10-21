    private boolean handleInterruptedException(final LogEvent memento) {
        final boolean appendSuccessful = queue.offer(memento);
        if (!appendSuccessful) {
            LOGGER.warn("Interrupted while waiting for a free slot in the AsyncAppender LogEvent-queue {}",
                getName());
        }
        // set the interrupted flag again.
        Thread.currentThread().interrupt();
        return appendSuccessful;
    }
