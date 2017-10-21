    public long getUserCount() {
        long count = 0;
        try {
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager mgr = roller.getUserManager();
            count = mgr.getUserCount();            
        } catch (WebloggerException e) {
            log.error("Error getting user count for site", e);
        }
        return count;
    }
