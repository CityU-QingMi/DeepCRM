    public String delete() {

        BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();

        try {
            WeblogBookmark bookmark;
            String bookmarks[] = getSelectedBookmarks();
            if (null != bookmarks && bookmarks.length > 0) {
                if (log.isDebugEnabled()) {
                    log.debug("Processing delete of " + bookmarks.length
                            + " bookmarks.");
                }
                for (int j = 0; j < bookmarks.length; j++) {
                    if (log.isDebugEnabled()) {
                        log.debug("Deleting bookmark - " + bookmarks[j]);
                    }
                    bookmark = bmgr.getBookmark(bookmarks[j]);
                    if (bookmark != null) {
                        bmgr.removeBookmark(bookmark);
                    }

                }
            }

            // flush changes
            WebloggerFactory.getWeblogger().flush();

            // notify caches
            CacheManager.invalidate(getActionWeblog());

        } catch (WebloggerException ex) {
            log.error("Error doing bookmark deletes", ex);
            addError("Error doing bookmark deletes");
        }

        return execute();
    }
