    public Subscription getSubscription(String feedUrl)
    throws RollerException {
        TypedQuery<Subscription> q = strategy.getNamedQuery("Subscription.getByFeedURL", Subscription.class);
        q.setParameter(1, feedUrl);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
