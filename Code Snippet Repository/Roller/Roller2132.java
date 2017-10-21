    public WeblogWrapper getWeblog(String handle) {
        WeblogWrapper wrappedWebsite = null;
        try {            
            Weblog website = WebloggerFactory.getWeblogger().getWeblogManager().getWeblogByHandle(handle);
            wrappedWebsite = WeblogWrapper.wrap(website, urlStrategy);
        } catch (Exception e) {
            log.error("ERROR: fetching users by letter", e);
        }
        return wrappedWebsite;
    }
