    public String clear() {
        
        // see if a specific cache was specified
        String handlerClass = getCache();
        if(handlerClass != null && handlerClass.length() > 0) {
            CacheManager.clear(handlerClass);
        } else {
            CacheManager.clear();
        }
        
        // update stats after clear
        myPrepare();
        
        return SUCCESS;
    }
