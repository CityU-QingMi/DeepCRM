    public List<Subscription> getTopSubscriptions(
            PlanetGroup group, int offset, int len) throws RollerException {
        List<Subscription> result;
        if (group != null) {
            TypedQuery<Subscription> q = strategy.getNamedQuery(
                    "Subscription.getByGroupOrderByInboundBlogsDesc", Subscription.class);
            q.setParameter(1, group);
            if (offset != 0) {
                q.setFirstResult(offset);
            }
            if (len != -1) {
                q.setMaxResults(len);
            }
            result = q.getResultList();
        } else {
            TypedQuery<Subscription> q = strategy.getNamedQuery(
                    "Subscription.getAllOrderByInboundBlogsDesc", Subscription.class);
            if (offset != 0) {
                q.setFirstResult(offset);
            }
            if (len != -1) {
                q.setMaxResults(len);
            }
            result = q.getResultList();
        }
        return result;
    }
