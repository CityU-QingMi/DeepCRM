    private void handlePingError(PingQueueEntry pingQueueEntry, Exception ex)
            throws WebloggerException {
        
        if ((pingQueueEntry.incrementAttempts() < PingConfig.getMaxPingAttempts()) && WeblogUpdatePinger.shouldRetry(ex)) {
            // We have attempts remaining, and it looks like we should retry,
            // so requeue the entry for processing on subsequent rounds
            LOGGER.debug("Error on ping attempt (" + pingQueueEntry.getAttempts() + ") for " + pingQueueEntry + ": [" + ex.getMessage() + "]. Will re-queue for later attempts.");
            LOGGER.debug("Error on last ping attempt was: ", ex);
            pingQueueMgr.saveQueueEntry(pingQueueEntry);
        } else {
            // Remove the entry
            LOGGER.warn("Error on ping attempt (" + pingQueueEntry.getAttempts() + ") for " + pingQueueEntry + ": [" + ex.getMessage() + "].  Entry will be REMOVED from ping queue.");
            LOGGER.debug("Error on last ping attempt was: ", ex);
            pingQueueMgr.removeQueueEntry(pingQueueEntry);
            // TODO: mark ping target invalid?
        }
    }
