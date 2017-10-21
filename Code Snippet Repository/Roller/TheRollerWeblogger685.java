    public void myPrepare() {
        if (StringUtils.isEmpty(bean.getId())) {
            // Create and initialize new, not-yet-saved WeblogBookmark
            bookmark = new WeblogBookmark();
            BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
            try {
                if (!StringUtils.isEmpty(getFolderId())) {
                    bookmark.setFolder(bmgr.getFolder(getFolderId()));
                }
            } catch (WebloggerException ex) {
                addError("generic.error.check.logs");
                log.error("Error looking up folder", ex);
            }
        } else {
            // existing bookmark, retrieve its info from DB
            try {
                BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
                bookmark = bmgr.getBookmark(getBean().getId());
            } catch (WebloggerException ex) {
                addError("generic.error.check.logs");
                log.error("Error looking up bookmark" + getBean().getId(), ex);
            }
        }
    }
