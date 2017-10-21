    public List<SubscriptionEntry> getEntries(Subscription sub, int offset, int len) throws RollerException {
        if (sub == null) {
            throw new WebloggerException("subscription cannot be null");
        }
        TypedQuery<SubscriptionEntry> q = strategy.getNamedQuery("SubscriptionEntry.getBySubscription", SubscriptionEntry.class);
        q.setParameter(1, sub);
        if (offset != 0) {
            q.setFirstResult(offset);
        }
        if (len != -1) {
            q.setMaxResults(len);
        }
        return q.getResultList();
    }
