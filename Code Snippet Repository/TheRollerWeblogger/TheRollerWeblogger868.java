    public Cache constructCache(Map properties) {
        int size = 100;
        String id = "unknown";
        
        try {
            size = Integer.parseInt((String) properties.get("size"));
        } catch(Exception e) {
            // ignored
        }
        
        String cacheId = (String) properties.get("id");
        if (cacheId != null) {
            id = cacheId;
        }
        
        Cache cache = new LRUCacheImpl(id, size);
        
        log.debug("new cache constructed. size="+size);
        
        return cache;
    }
