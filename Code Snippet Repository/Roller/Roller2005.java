    public List<WeblogEntry> retrieveWeblogEntries(boolean publishedOnly) throws WebloggerException {
        WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
        wesc.setWeblog(weblog);
        wesc.setCatName(this.getName());
        if (publishedOnly) {
            wesc.setStatus(PubStatus.PUBLISHED);
        }
        return wmgr.getWeblogEntries(wesc);
    }
