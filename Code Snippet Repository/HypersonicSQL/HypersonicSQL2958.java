    public HsqlArrayList listVisibleUsers(Session session) {

        HsqlArrayList list;
        User          user;
        boolean       isAdmin;
        String        sessionName;
        String        userName;

        list        = new HsqlArrayList();
        isAdmin     = session.isAdmin();
        sessionName = session.getUsername();

        if (userList == null || userList.size() == 0) {
            return list;
        }

        for (int i = 0; i < userList.size(); i++) {
            user = (User) userList.get(i);

            if (user == null) {
                continue;
            }

            userName = user.getName().getNameString();

            if (isAdmin) {
                list.add(user);
            } else if (sessionName.equals(userName)) {
                list.add(user);
            }
        }

        return list;
    }
