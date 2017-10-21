    public WeblogCategory getWeblogCategory(String categoryName) {
        WeblogCategory category = null;
        try {
            Weblogger roller = WebloggerFactory.getWeblogger();
            WeblogEntryManager wmgr = roller.getWeblogEntryManager();
            if (categoryName != null && !categoryName.equals("nil")) {
                category = wmgr.getWeblogCategoryByName(this, categoryName);
            } else {
                category = getWeblogCategories().iterator().next();
            }
        } catch (WebloggerException e) {
            log.error("ERROR: fetching category: " + categoryName, e);
        }
        return category;
    }
