    public void myPrepare() {
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            if(!StringUtils.isEmpty(getRemoveId())) {
                setCategory(wmgr.getWeblogCategory(getRemoveId()));
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up category", ex);
        }
    }
