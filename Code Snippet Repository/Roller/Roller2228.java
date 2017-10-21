    public WeblogCategory getWeblogCategory() {
        
        if(weblogCategory == null && weblogCategoryName != null) {
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
                weblogCategory = wmgr.getWeblogCategoryByName(getWeblog(), weblogCategoryName);
            } catch (WebloggerException ex) {
                log.error("Error getting weblog category "+weblogCategoryName, ex);
            }
        }
        
        return weblogCategory;
    }
