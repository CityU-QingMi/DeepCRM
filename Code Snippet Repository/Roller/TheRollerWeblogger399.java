    public void addBookmark(WeblogBookmark bookmark) {
        for (WeblogBookmark bookmarkItem : bookmarks) {
            if (bookmarkItem.getId().equals(bookmark.getId())) {
                // already in bookmark list
                return;
            }
        }
        bookmark.setFolder(this);
        getBookmarks().add(bookmark);
    }
