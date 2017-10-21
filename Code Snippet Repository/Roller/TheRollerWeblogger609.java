    public WeblogEntry getWeblogEntry() {
        
        if(weblogEntry == null && 
                (previewEntry != null || super.getWeblogAnchor() != null)) {
            
            String anchor = previewEntry;
            if(previewEntry == null) {
                anchor = super.getWeblogAnchor();
            }
            
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
                weblogEntry = wmgr.getWeblogEntryByAnchor(getWeblog(), anchor);
            } catch (WebloggerException ex) {
                log.error("Error getting weblog entry "+anchor, ex);
            }
        }
        
        return weblogEntry;
    }
