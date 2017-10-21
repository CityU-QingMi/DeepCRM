    public GenericThrottle(int thresh, int inter, int maxEntries) {
        
        // threshold can't be negative, that would mean everyone is abusive
        if(thresh > -1) {
            this.threshold = thresh;
        }
        
        // interval must be a positive value
        if(inter > 0) {
            this.interval = inter;
        }
        
        // max entries must be a positive value
        if(maxEntries < 0) {
            maxEntries = 1;
        }
        
        // cache props
        Map<String,String> cacheProps = new HashMap<String,String>();
        cacheProps.put("id", "throttle");
        cacheProps.put("size", ""+maxEntries);
        cacheProps.put("timeout", ""+this.interval);
        
        // get cache instance.  handler is null cuz we don't want to register it
        this.clientHistoryCache = CacheManager.constructCache(null, cacheProps);
    }
