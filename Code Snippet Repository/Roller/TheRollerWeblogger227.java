    private void processQueueEntry(PingQueueEntry pingQueueEntry) throws WebloggerException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Processing ping queue entry: " + pingQueueEntry);
        }
        
        PingTarget pingTarget = pingQueueEntry.getPingTarget();
        Weblog website = pingQueueEntry.getWebsite();
        boolean pingSucceeded = false;
        if (PingConfig.getLogPingsOnly()) {
            // Just log the ping and pretend it succeeded.
            LOGGER.info("Logging simulated ping for ping queue entry " + pingQueueEntry);
            pingSucceeded = true;
        } else {
            // Actually process the ping
            try {
                // Send the ping
                WeblogUpdatePinger.sendPing(pingTarget, website);
                // Consider successful ping transmission if we didn't get an exception.  We don't care here
                // about the result of the ping if it was transmitted.
                pingSucceeded = true;
            } catch (Exception ex) {
                // Handle the ping error, either removing or requeuing the ping queue entry.
                handlePingError(pingQueueEntry, ex);
            }
        }
        // We do this outside of the previous try-catch because we don't want an exception here to be considered a ping error.
        if (pingSucceeded) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Processed ping: " + pingQueueEntry);
            }
            pingQueueMgr.removeQueueEntry(pingQueueEntry);
        }
    }
