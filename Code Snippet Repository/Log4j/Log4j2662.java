    @Override
    protected boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
    	boolean closed = true;
        LOGGER.debug("Shutting down FlumePersistentManager");
        worker.shutdown();
        final long requestedTimeoutMillis = timeUnit.toMillis(timeout);
        final long shutdownWaitMillis = requestedTimeoutMillis > 0 ? requestedTimeoutMillis : SHUTDOWN_WAIT_MILLIS;
		try {
            worker.join(shutdownWaitMillis);
        } catch (final InterruptedException ie) {
            // Ignore the exception and shutdown.
        }
        ExecutorServices.shutdown(threadPool, shutdownWaitMillis, TimeUnit.MILLISECONDS, toString());
        try {
            worker.join();
        } catch (final InterruptedException ex) {
            logDebug("interrupted while waiting for worker to complete", ex);
        }
        try {
            LOGGER.debug("FlumePersistenceManager dataset status: {}", database.getStats(new StatsConfig()));
            database.close();
        } catch (final Exception ex) {
            logWarn("Failed to close database", ex);
            closed = false;
        }
        try {
            environment.cleanLog();
            environment.close();
        } catch (final Exception ex) {
            logWarn("Failed to close environment", ex);
            closed = false;
        }
        return closed && super.releaseSub(timeout, timeUnit);
    }
