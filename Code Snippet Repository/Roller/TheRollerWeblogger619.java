    public Date getLastModified() {
        
        Date lastModified = null;
        
        // first try our cached version
        if(this.lastUpdateTime != null) {
            lastModified = (Date) this.lastUpdateTime.getValue();
        }
        
        // still null, we need to get a fresh value
        if(lastModified == null) {
            
            // TODO: create a WeblogManager.getLastUpdated() method to use below
            lastModified = null;
            
            if (lastModified == null) {
                lastModified = new Date();
                log.warn("Can't get lastUpdate time, using current time instead");
            }
            
            this.lastUpdateTime = new ExpiringCacheEntry(lastModified, this.timeout);
        }
        
        return lastModified;
    }
