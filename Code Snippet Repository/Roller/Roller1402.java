    public synchronized void processQueue() {
        if (PingConfig.getSuspendPingProcessing()) {
            LOGGER.info("Ping processing has been suspended.  Skipping current round of ping queue processing.");
            return;
        }
        
        String absoluteContextUrl = WebloggerRuntimeConfig.getAbsoluteContextURL();
        if (absoluteContextUrl == null) {
            LOGGER.warn("WARNING: Skipping current ping queue processing round because we cannot yet determine the site's absolute context url.");
            return;
        }
        
        // TODO: Group by ping target and ping all sites for that target?
        // We're currently not taking advantage of grouping by ping target site and then sending
        // all of the pings for that target at once.  If it becomes an efficiency issue, we should do
        // that.
        
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Started processing ping queue.");
            }
            // Get all of the entries
            List<PingQueueEntry> entries = pingQueueMgr.getAllQueueEntries();
            
            // Process each entry
            for (PingQueueEntry pingQueueEntry : entries) {
                processQueueEntry(pingQueueEntry);
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Finished processing ping queue.");
            }
        } catch (Exception ex) {
            LOGGER.error("Unexpected exception processing ping queue!  Aborting this pass of ping queue processing.", ex);
        }
    }
