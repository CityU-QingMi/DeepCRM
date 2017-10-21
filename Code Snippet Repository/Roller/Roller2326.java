    public String move() {

        try {
            BookmarkManager bmgr = WebloggerFactory.getWeblogger()
                    .getBookmarkManager();

            if (log.isDebugEnabled()) {
                log.debug("Moving bookmarks to folder - "
                        + getTargetFolderId());
            }

            // Move bookmarks to new parent folder.
            WeblogBookmarkFolder newFolder = bmgr.getFolder(getTargetFolderId());
            String bookmarks[] = getSelectedBookmarks();
            if (null != bookmarks && bookmarks.length > 0) {
                for (int j = 0; j < bookmarks.length; j++) {
                    WeblogBookmark bd = bmgr.getBookmark(bookmarks[j]);
                    newFolder.addBookmark(bd);
                    bd.setFolder(newFolder);
                    bmgr.saveBookmark(bd);
                    folder.getBookmarks().remove(bd);
                }
            }

            // flush changes
            WebloggerFactory.getWeblogger().flush();

            // notify caches
            CacheManager.invalidate(getActionWeblog());

        } catch (WebloggerException e) {
            log.error("Error doing bookmark move", e);
            addError("bookmarksForm.error.move");
        }

        return execute();
    }
