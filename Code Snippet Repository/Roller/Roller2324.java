    public String deleteFolder() {

        try {
            BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
            WeblogBookmarkFolder fd = bmgr.getFolder(getFolderId());
            if (fd != null) {
                bmgr.removeFolder(fd);

                // flush changes
                WebloggerFactory.getWeblogger().flush();

                // notify caches
                CacheManager.invalidate(getActionWeblog());

                // re-route to default folder
                setFolder(bmgr.getDefaultFolder(getActionWeblog()));
                setFolderId(getFolder().getId());
            }
        } catch (WebloggerException ex) {
            log.error("Error deleting folder", ex);
        }
        return execute();
    }
