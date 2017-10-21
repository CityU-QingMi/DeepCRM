    public void runTask() {
        
        try {
            log.info("task started");
            
            WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            mgr.resetAllHitCounts();
            WebloggerFactory.getWeblogger().flush();
            
            log.info("task completed");
            
        } catch (WebloggerException e) {
            log.error("Error while resetting hit counts", e);
        } catch (Exception ee) {
            log.error("unexpected exception", ee);
        } finally {
            // always release
            WebloggerFactory.getWeblogger().release();
        }
        
    }
