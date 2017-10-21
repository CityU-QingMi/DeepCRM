    public boolean processHit(String clientId) {
        
        if(clientId == null) {
            return false;
        }
        
        // see if we have any info about this client yet
        ClientInfo client = null;
        ExpiringCacheEntry cacheEntry = (ExpiringCacheEntry) this.clientHistoryCache.get(clientId);
        if(cacheEntry != null) {
            log.debug("HIT "+clientId);
            client = (ClientInfo) cacheEntry.getValue();
            
            // this means entry had expired
            if(client == null) {
                log.debug("EXPIRED "+clientId);
                this.clientHistoryCache.remove(clientId);
            }
        }
        
        // if we already know this client then update their hit count and 
        // see if they have surpassed the threshold
        if(client != null) {
            client.hits++;
            
            log.debug("STATUS "+clientId+" - "+client.hits+" hits since "+client.start);
            
            // abusive client
            if(client.hits > this.threshold) {
                return true;
            }
            
        } else {
            log.debug("NEW "+clientId);
            
            // first timer
            ClientInfo newClient = new ClientInfo();
            newClient.hits = 1;

            ExpiringCacheEntry newEntry = new ExpiringCacheEntry(newClient, this.interval);
            this.clientHistoryCache.put(clientId, newEntry);
        }
        
        return false;
    }
