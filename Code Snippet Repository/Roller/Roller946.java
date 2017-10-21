    public void revokeRole(String roleName, User user) throws WebloggerException {
        TypedQuery<UserRole> q = strategy.getNamedQuery("UserRole.getByUserNameAndRole", UserRole.class);
        q.setParameter(1, user.getUserName());
        q.setParameter(2, roleName);
        try {
            UserRole role = q.getSingleResult();
            this.strategy.remove(role);

        } catch (NoResultException e) {
            throw new WebloggerException("ERROR: removing role", e);
        }
    }
