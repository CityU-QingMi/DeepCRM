    public long getCommentCount() {
        long count = 0;
        try {
            Weblogger roller = WebloggerFactory.getWeblogger();
            WeblogEntryManager mgr = roller.getWeblogEntryManager();
            count = mgr.getCommentCount();            
        } catch (WebloggerException e) {
            log.error("Error getting comment count for site ", e);
        }
        return count;
    }
