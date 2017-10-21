    public UserWrapper getUser(String username) {
        UserWrapper wrappedUser = null;
        try {            
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager umgr = roller.getUserManager();
            User user = umgr.getUserByUserName(username, Boolean.TRUE);
            wrappedUser = UserWrapper.wrap(user);
        } catch (Exception e) {
            log.error("ERROR: fetching users by letter", e);
        }
        return wrappedUser;
    }
