    public User get(String name) {

        User user = (User) userList.get(name);

        if (user == null) {
            throw Error.error(ErrorCode.X_28501, name);
        }

        return user;
    }
