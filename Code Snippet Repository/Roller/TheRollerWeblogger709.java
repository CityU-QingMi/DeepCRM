    public List<WeblogCategory> getCategories() {
        // make list of categories with first option being being a transient
        // category just meant to represent the default option of any category
        List<WeblogCategory> cats = new ArrayList<WeblogCategory>();
        
        WeblogCategory tmpCat = new WeblogCategory();
        tmpCat.setName("Any");
        cats.add(tmpCat);
        
        List<WeblogCategory> weblogCats = Collections.emptyList();
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            weblogCats = wmgr.getWeblogCategories(getActionWeblog());
        } catch (WebloggerException ex) {
            log.error("Error getting category list for weblog - " + getWeblog(), ex);
        }
        
        cats.addAll(weblogCats);
        
        return cats;
    }
