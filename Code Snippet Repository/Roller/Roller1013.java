    public List<User> getUsersByLetter(char letter, int offset, int length)
            throws WebloggerException {
        TypedQuery<User> query = strategy.getNamedQuery(
                "User.getByUserNameOrderByUserName", User.class);
        query.setParameter(1, letter + "%");
        if (offset != 0) {
            query.setFirstResult(offset);
        }
        if (length != -1) {
            query.setMaxResults(length);
        }
        return query.getResultList();
    }
