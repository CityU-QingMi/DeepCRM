    public WeblogHitCount getHitCountByWeblog(Weblog weblog)
    throws WebloggerException {
        TypedQuery<WeblogHitCount> q = strategy.getNamedQuery("WeblogHitCount.getByWeblog", WeblogHitCount.class);
        q.setParameter(1, weblog);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
