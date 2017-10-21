    public void myPrepare() {
        if (getRemoveId() != null) {
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger()
                        .getWeblogEntryManager();
                setRemoveEntry(wmgr.getWeblogEntry(getRemoveId()));
            } catch (WebloggerException ex) {
                log.error("Error looking up entry by id - " + getRemoveId(), ex);
            }
        }
    }
