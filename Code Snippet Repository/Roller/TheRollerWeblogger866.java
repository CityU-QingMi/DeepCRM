    public Cache constructCache(Map properties) {
        
        int size = 100;
        long timeout = 15 * 60;
        String id = "unknown";
        
        try {
            size = Integer.parseInt((String) properties.get("size"));
        } catch(Exception e) {
            // ignored
        }
        
        try {
            timeout = Long.parseLong((String) properties.get("timeout"));
        } catch(Exception e) {
            // ignored
        }
        
        String cacheId = (String) properties.get("id");
        if(cacheId != null) {
            id = cacheId;
        }
        
        Cache cache = new ExpiringLRUCacheImpl(id, size, timeout);
        
        log.debug("new cache constructed. size=" + size + ", timeout=" + timeout);
        
        return cache;
    }
