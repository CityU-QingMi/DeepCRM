    public List<WeblogBookmark> getBookmarks(WeblogBookmarkFolder folder)
            throws WebloggerException {
        TypedQuery<WeblogBookmark> query;
        List<WeblogBookmark> results;

        query = strategy.getNamedQuery("BookmarkData.getByFolder", WeblogBookmark.class);
        query.setParameter(1, folder);
        results = query.getResultList();

        return results;
    }
