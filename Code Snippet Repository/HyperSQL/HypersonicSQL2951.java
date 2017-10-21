    public User createUser(Session session, HsqlName name, String password,
                           boolean isDigest) {

        // This will throw an appropriate exception if grantee already exists,
        // regardless of whether the name is in any User, Role, etc. list.
        User user = granteeManager.addUser(name);

        if (session == null) {
            user.setPassword(password, isDigest);
        } else {
            try {
                setPassword(session, user, password, isDigest);
            } catch (HsqlException e) {
                granteeManager.removeNewUser(name);

                throw e;
            }
        }

        // this cannot fail
        userList.add(name.name, user);

        return user;
    }
