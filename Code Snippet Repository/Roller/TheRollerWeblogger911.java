    private Hashtable createCategoryStruct(WeblogCategory category, String userid) {
        
        Hashtable struct = new Hashtable();
        struct.put("title", category.getName());
        struct.put("description", category.getName());
        
        Weblogger roller = WebloggerFactory.getWeblogger();
        URLStrategy strategy = roller.getUrlStrategy();
        
        String catUrl = strategy.getWeblogCollectionURL(category.getWeblog(),
        		null, category.getName(), null, null, 0, true);
        struct.put("htmlUrl", catUrl);
        
        String rssUrl = strategy.getWeblogFeedURL(category.getWeblog(),
               null, "entries", "rss", category.getName(), null, null, false, true);
        struct.put("rssUrl",rssUrl);
        
        return struct;
    }
