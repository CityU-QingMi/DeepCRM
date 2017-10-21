    public void run() {
        try {            
            // Update all feeds in planet
            log.info("Refreshing Planet entries");
            FeedUpdater updater = new SingleThreadedFeedUpdater();
            updater.updateSubscriptions();
            WebloggerFactory.getWeblogger().release();
            
        } catch (Exception e) {
            log.error("ERROR refreshing planet", e);
        }
    }
