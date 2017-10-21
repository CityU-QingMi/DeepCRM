    public void execute() {
        
        log.debug("starting");
        
        // check inputs to see what work we are going to do
        if(inputs != null) {
            
            // what weblogs will we handle?
            List<String> weblogs = (List<String>) inputs.get("weblogs");
            if(weblogs == null) {
                return;
            }
            
            // should we do rss entries feeds?
            if("true".equals(inputs.get("feed-entries-rss"))) {
                this.warmupFeedCache(weblogs, "entries", "rss");
            }
            
            // should we do atom entries feeds?
            if("true".equals(inputs.get("feed-entries-atom"))) {
                this.warmupFeedCache(weblogs, "entries", "atom");
            }
        }
        
        log.debug("finished");
    }
