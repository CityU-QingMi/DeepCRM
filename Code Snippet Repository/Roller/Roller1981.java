    public WeblogBookmarkFolder getBookmarkFolder(String folderName) {
        try {
            Weblogger roller = WebloggerFactory.getWeblogger();
            BookmarkManager bmgr = roller.getBookmarkManager();
            if (folderName == null || folderName.equals("nil") || folderName.trim().equals("/")) {
                return bmgr.getDefaultFolder(this);
            } else {
                return bmgr.getFolder(this, folderName);
            }
        } catch (WebloggerException re) {
            log.error("ERROR: fetching folder for weblog", re);
        }
        return null;
    }
