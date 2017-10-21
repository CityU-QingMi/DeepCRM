    private WeblogEntry getNextEntry() {
        if (nextEntry == null) {
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogEntryManager wmgr = roller.getWeblogEntryManager();
                nextEntry = wmgr.getNextEntry(currEntry, null, locale);
                // make sure that entry is published and not to future
                if (nextEntry != null && nextEntry.getPubTime().after(new Date())
                        && nextEntry.getStatus().equals(PubStatus.PUBLISHED)) {
                    nextEntry = null;
                }
            } catch (WebloggerException e) {
                log.error("ERROR: getting next entry", e);
            }
        }

        return nextEntry;
    }
