    public String execute() {
        
        try {
            WeblogManager mgr =  WebloggerFactory.getWeblogger().getWeblogManager();
            setWeblogs(mgr.getWeblogs(true, null, null, null, 0, -1));
        } catch (WebloggerException ex) {
            LOG.error("Error getting weblogs", ex);
            addError("frontpageConfig.weblogs.error");
        }

        try {
            setUserCount(WebloggerFactory.getWeblogger().getUserManager().getUserCount());
            setBlogCount(WebloggerFactory.getWeblogger().getWeblogManager().getWeblogCount());
        } catch (WebloggerException ex) {
            LOG.error("Error getting user/weblog counts", ex);
        }
        
        return SUCCESS;
    }
