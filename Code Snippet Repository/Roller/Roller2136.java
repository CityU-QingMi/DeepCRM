    public List<StatCount> getHotWeblogs(int sinceDays, int length) {
        
        List<StatCount> results = new ArrayList<StatCount>();
        try {
            WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            List<WeblogHitCount> hotBlogs = mgr.getHotWeblogs(sinceDays, 0, length);
            
            for (WeblogHitCount hitCount : hotBlogs) {
                StatCount statCount = new StatCount(
                    hitCount.getWeblog().getId(),
                    hitCount.getWeblog().getHandle(),
                    hitCount.getWeblog().getName(),
                    "statCount.weblogDayHits",
                    hitCount.getDailyHits());
                statCount.setWeblogHandle(hitCount.getWeblog().getHandle());
                results.add(statCount);              
            }
            
        } catch (Exception e) {
            log.error("ERROR: fetching hot weblog list", e);
        }
        
        return results;
    }
