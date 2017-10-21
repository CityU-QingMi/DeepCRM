    public List<WeblogEntryWrapper> getPinnedWeblogEntries(int length) {
        List<WeblogEntryWrapper> results = new ArrayList<WeblogEntryWrapper>();
        try {            
            Weblogger roller = WebloggerFactory.getWeblogger();
            WeblogEntryManager wmgr = roller.getWeblogEntryManager();
            List<WeblogEntry> weblogEntries = wmgr.getWeblogEntriesPinnedToMain(length);
            for (WeblogEntry entry : weblogEntries) {
                results.add(WeblogEntryWrapper.wrap(entry, urlStrategy));
            }
        } catch (Exception e) {
            log.error("ERROR: fetching pinned weblog entries", e);
        }
        return results;
    }
