    public WeblogBookmarkFolder getDefaultFolder(Weblog weblog)
            throws WebloggerException {

        if (weblog == null) {
            throw new WebloggerException("weblog is null");
        }
        
        TypedQuery<WeblogBookmarkFolder> q = strategy.getNamedQuery("WeblogBookmarkFolder.getByWebsite&Name",
                WeblogBookmarkFolder.class);
        q.setParameter(1, weblog);
        q.setParameter(2, "default");
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
