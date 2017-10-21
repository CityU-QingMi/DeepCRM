    public void setPassword(Session session, User user, String password,
                            boolean isDigest) {

        if (!isDigest && !checkComplexity(session, password)) {
            throw Error.error(ErrorCode.PASSWORD_COMPLEXITY);
        }

        // requires: UserManager.createSAUser(), UserManager.createPublicUser()
        user.setPassword(password, isDigest);
    }
