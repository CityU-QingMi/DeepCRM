    public WeblogEntry getWeblogEntry() {

        if (weblogEntry == null && weblogAnchor != null) {
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger()
                        .getWeblogEntryManager();
                weblogEntry = wmgr.getWeblogEntryByAnchor(getWeblog(),
                        weblogAnchor);
            } catch (WebloggerException ex) {
                log.error("Error getting weblog entry " + weblogAnchor, ex);
            }
        }

        return weblogEntry;
    }
