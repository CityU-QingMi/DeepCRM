    private boolean rollover(final RolloverStrategy strategy) {

        boolean releaseRequired = false;
        try {
            // Block until the asynchronous operation is completed.
            semaphore.acquire();
            releaseRequired = true;
        } catch (final InterruptedException e) {
            logError("Thread interrupted while attempting to check rollover", e);
            return false;
        }

        boolean success = true;

        try {
            final RolloverDescription descriptor = strategy.rollover(this);
            if (descriptor != null) {
                writeFooter();
                closeOutputStream();
                if (descriptor.getSynchronous() != null) {
                    LOGGER.debug("RollingFileManager executing synchronous {}", descriptor.getSynchronous());
                    try {
                        success = descriptor.getSynchronous().execute();
                    } catch (final Exception ex) {
                        success = false;
                        logError("Caught error in synchronous task", ex);
                    }
                }

                if (success && descriptor.getAsynchronous() != null) {
                    LOGGER.debug("RollingFileManager executing async {}", descriptor.getAsynchronous());
                    asyncExecutor.execute(new AsyncAction(descriptor.getAsynchronous(), this));
                    releaseRequired = false;
                }
                return true;
            }
            return false;
        } finally {
            if (releaseRequired) {
                semaphore.release();
            }
        }

    }
