    public boolean isAbusive(String clientId) {
        
        if(clientId == null) {
            return false;
        }
        
        // see if we have any info about this client
        ClientInfo client = null;
        ExpiringCacheEntry cacheEntry = (ExpiringCacheEntry) this.clientHistoryCache.get(clientId);
        if(cacheEntry != null) {
            log.debug("HIT "+clientId);
            client = (ClientInfo) cacheEntry.getValue();
            
            // this means entry had expired
            if (client == null) {
                log.debug("EXPIRED "+clientId);
                this.clientHistoryCache.remove(clientId);
            }
        }

        return client != null && client.hits > this.threshold;
    }
