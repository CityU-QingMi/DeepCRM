    @Override
    public synchronized Object get(String key) {
        
        Object value = null;
        ExpiringCacheEntry entry = null;
        
        synchronized(this) {
            entry = (ExpiringCacheEntry) super.get(key);
        }
        
        if (entry != null) {
            
            value = entry.getValue();
            
            // if the value is null then that means this entry expired
            if (value == null) {
                log.debug("EXPIRED ["+key+"]");
                hits--;
                super.remove(key);
            }
        }
        
        return value;
    }
