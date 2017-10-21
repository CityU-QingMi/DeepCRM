    public Map getEntries() {
        if (entries == null) {
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogEntryManager wmgr = roller.getWeblogEntryManager();
                currEntry = wmgr.getWeblogEntryByAnchor(weblog, entryAnchor);
                if (currEntry != null && currEntry.getStatus().equals(PubStatus.PUBLISHED)) {
                    entries = new TreeMap();
                    entries.put(new Date(currEntry.getPubTime().getTime()),Collections.singletonList(WeblogEntryWrapper.wrap(currEntry, urlStrategy)));
                }
            } catch (Exception e) {
                log.error("ERROR: fetching entry");
            }
        }


        
        return entries;
    }
