    public WeblogBookmarkFolder getFolder(Weblog website, String name)
            throws WebloggerException {

        // Do simple lookup by name
        TypedQuery<WeblogBookmarkFolder> query = strategy.getNamedQuery("WeblogBookmarkFolder.getByWebsite&Name",
                WeblogBookmarkFolder.class);
        query.setParameter(1, website);
        query.setParameter(2, name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
