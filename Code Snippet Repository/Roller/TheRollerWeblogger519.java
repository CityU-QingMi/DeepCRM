    public List<UserWrapper> getNewUsers(int sinceDays, int length) {
        List<UserWrapper> results = new ArrayList<UserWrapper>();
        try {            
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager umgr = roller.getUserManager();
            List<User> users = umgr.getUsers(Boolean.TRUE, null, null, 0, length);
            for (User user : users) {
                results.add(UserWrapper.wrap(user));
            }
        } catch (Exception e) {
            log.error("ERROR: fetching weblog list", e);
        }
        return results;
    }   
