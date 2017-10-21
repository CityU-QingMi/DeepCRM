    public void runTask() {
        try {
            log.info("Refreshing Planet subscriptions");
            
            FeedUpdater updater = new SingleThreadedFeedUpdater();
            updater.updateSubscriptions();
            
        } catch (Exception e) {
            log.error("ERROR refreshing planet", e);
        } finally {
            // always release
            WebloggerFactory.getWeblogger().release();
        }
    }
