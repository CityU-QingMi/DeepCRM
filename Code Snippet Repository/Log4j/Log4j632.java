        @Override
        public void run() {
            while (!shutdown) {
                LogEvent event;
                try {
                    event = queue.take();
                    if (event == SHUTDOWN_LOG_EVENT) {
                        shutdown = true;
                        continue;
                    }
                } catch (final InterruptedException ex) {
                    break; // LOG4J2-830
                }
                event.setEndOfBatch(queue.isEmpty());
                final boolean success = callAppenders(event);
                if (!success && errorAppender != null) {
                    try {
                        errorAppender.callAppender(event);
                    } catch (final Exception ex) {
                        // Silently accept the error.
                    }
                }
            }
            // Process any remaining items in the queue.
            LOGGER.trace("AsyncAppender.AsyncThread shutting down. Processing remaining {} queue events.",
                queue.size());
            int count = 0;
            int ignored = 0;
            while (!queue.isEmpty()) {
                try {
                    final LogEvent event = queue.take();
                    if (event instanceof Log4jLogEvent) {
                        final Log4jLogEvent logEvent = (Log4jLogEvent) event;
                        logEvent.setEndOfBatch(queue.isEmpty());
                        callAppenders(logEvent);
                        count++;
                    } else {
                        ignored++;
                        LOGGER.trace("Ignoring event of class {}", event.getClass().getName());
                    }
                } catch (final InterruptedException ex) {
                    // May have been interrupted to shut down.
                    // Here we ignore interrupts and try to process all remaining events.
                }
            }
            LOGGER.trace("AsyncAppender.AsyncThread stopped. Queue has {} events remaining. "
                + "Processed {} and ignored {} events since shutdown started.", queue.size(), count, ignored);
        }
