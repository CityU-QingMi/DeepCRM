    public synchronized Object get(String key) {
        
        Object obj = this.cache.get(key);
        
        // for metrics
        if(obj == null) {
            misses++;
        } else {
            hits++;
        }
        
        return obj;
    }
