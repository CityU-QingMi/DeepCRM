    public static void teardownUser(String userName) throws Exception {

        // lookup the user
        UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
        User user = mgr.getUserByUserName(userName, null);

        // remove the user
        mgr.removeUser(user);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
