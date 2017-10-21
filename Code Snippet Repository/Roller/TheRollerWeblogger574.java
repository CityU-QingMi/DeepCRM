    public Map getEntries() {
        if (entries == null) {
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogEntryManager wmgr = roller.getWeblogEntryManager();
                currEntry = wmgr.getWeblogEntryByAnchor(weblog, entryAnchor);
                if (currEntry != null) {

                    // clone the entry since we don't want to work with the real pojo
                    WeblogEntry tmpEntry = new WeblogEntry();
                    tmpEntry.setData(currEntry);

                    // set the pubtime to the current time if it is unset
                    if(tmpEntry.getPubTime() == null) {
                        tmpEntry.setPubTime(new Timestamp(System.currentTimeMillis()));
                    }

                    // store the entry in the collection
                    entries = new TreeMap();
                    entries.put(tmpEntry.getPubTime(),Collections.singletonList(WeblogEntryWrapper.wrap(tmpEntry, urlStrategy)));
                }
            } catch (Exception e) {
                log.error("ERROR: fetching entry", e);
            }
        }
        return entries;
    }
