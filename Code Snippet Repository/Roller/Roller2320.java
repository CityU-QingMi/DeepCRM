    public void myPrepare() {
        try {
            BookmarkManager bmgr = WebloggerFactory.getWeblogger()
                    .getBookmarkManager();
            if (!StringUtils.isEmpty(getFolderId())) {
                setFolder(bmgr.getFolder(getFolderId()));
            } else {
                setFolder(bmgr.getDefaultFolder(getActionWeblog()));
                if (getFolder() != null) {
                    setFolderId(getFolder().getId());
                }
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up folder", ex);
        }
    }
