    @Override
    public User getUserByOpenIdUrl(String openIdUrl) throws WebloggerException {
        if (openIdUrl == null) {
            throw new WebloggerException("OpenID URL cannot be null");
        }

        TypedQuery<User> query;
        User user;
        query = strategy.getNamedQuery(
                "User.getByOpenIdUrl", User.class);
        query.setParameter(1, openIdUrl);
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }
