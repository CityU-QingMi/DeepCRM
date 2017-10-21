    public List<WeblogBookmarkFolder> getAllFolders(Weblog website)
            throws WebloggerException {
        if (website == null) {
            throw new WebloggerException("Website is null");
        }
        
        TypedQuery<WeblogBookmarkFolder> q = strategy.getNamedQuery("WeblogBookmarkFolder.getByWebsite",
                WeblogBookmarkFolder.class);
        q.setParameter(1, website);
        return q.getResultList();
    }
