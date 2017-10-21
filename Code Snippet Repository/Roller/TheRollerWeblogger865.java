    public ExpiringCacheEntry(Object value, long timeout) {
        this.value = value;
        
        // make sure that we don't support negative values
        if(timeout > 0) {
            this.timeout = timeout;
        }
        
        this.timeCached = System.currentTimeMillis();
    }
