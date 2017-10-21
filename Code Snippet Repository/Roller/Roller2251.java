    public Object get(String key) {
        
        if (!cacheEnabled) {
            return null;
        }
        
        Object entry = contentCache.get(key);
        
        if(entry == null) {
            log.debug("MISS "+key);
        } else {
            log.debug("HIT "+key);
        }
        
        return entry;
    }
