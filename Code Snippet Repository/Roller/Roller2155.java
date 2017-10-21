    public String editEntry(String anchor) {
        try {
            // need to determine entryId from anchor
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            WeblogEntry entry = wmgr.getWeblogEntryByAnchor(weblog, anchor);
            if(entry != null) {
                return urlStrategy.getEntryEditURL(weblog.getHandle(), entry.getId(), false);
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up entry by anchor - "+anchor, ex);
        }
        return null;
    } 
