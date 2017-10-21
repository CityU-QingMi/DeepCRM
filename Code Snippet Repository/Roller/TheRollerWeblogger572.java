    private WeblogEntry getPrevEntry() {
        if (prevEntry == null) {
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogEntryManager wmgr = roller.getWeblogEntryManager();
                prevEntry = wmgr.getPreviousEntry(currEntry, null, locale);
                // make sure that entry is published and not to future
                if (prevEntry != null && prevEntry.getPubTime().after(new Date())
                        && prevEntry.getStatus().equals(PubStatus.PUBLISHED)) {
                    prevEntry = null;
                }
            } catch (WebloggerException e) {
                log.error("ERROR: getting prev entry", e);
            }
        }

        return prevEntry;
    }
