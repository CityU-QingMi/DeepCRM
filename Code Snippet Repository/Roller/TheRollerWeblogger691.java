    public String view() {

        try {
            BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
            if (!StringUtils.isEmpty(viewFolderId)) {
                setFolder(bmgr.getFolder(viewFolderId));
                setFolderId(viewFolderId);
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up folder", ex);
        }
        return execute();
    }
