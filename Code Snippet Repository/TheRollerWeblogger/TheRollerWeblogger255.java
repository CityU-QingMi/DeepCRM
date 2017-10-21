    public void runTask() {
        
        log.debug("task started");
        
        try {
            WeblogEntryManager wMgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            IndexManager searchMgr = WebloggerFactory.getWeblogger().getIndexManager();
            
            Date now = new Date();
            
            log.debug("looking up scheduled entries older than " + now);
            
            // get all published entries older than current time
            WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
            wesc.setEndDate(now);
            wesc.setStatus(PubStatus.SCHEDULED);
            List<WeblogEntry> scheduledEntries = wMgr.getWeblogEntries(wesc);
            log.debug("promoting "+scheduledEntries.size()+" entries to PUBLISHED state");
            
            for (WeblogEntry entry : scheduledEntries) {
                entry.setStatus(PubStatus.PUBLISHED);
                entry.setRefreshAggregates(true);
                wMgr.saveWeblogEntry(entry);
            }

            // commit the changes
            WebloggerFactory.getWeblogger().flush();
            
            // take a second pass to trigger reindexing and cache invalidations
            // this is because we need the updated entries flushed first
            for (WeblogEntry entry : scheduledEntries) {
                // trigger a cache invalidation
                CacheManager.invalidate(entry);
                // trigger search index on entry
                searchMgr.addEntryReIndexOperation(entry);
            }

        } catch (WebloggerException e) {
            log.error("Error getting scheduled entries", e);
        } catch(Exception e) {
            log.error("Unexpected exception running task", e);
        } finally {
            // always release
            WebloggerFactory.getWeblogger().release();
        }
        
        log.debug("task completed");
        
    }
