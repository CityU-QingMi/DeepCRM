    public List getUsersWeblogs(String userName) {
        List results = new ArrayList();
        try {            
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager umgr = roller.getUserManager();
            User user = umgr.getUserByUserName(userName);
            List<WeblogPermission> perms = umgr.getWeblogPermissions(user);
            for (WeblogPermission perm : perms) {
                results.add(WeblogWrapper.wrap(perm.getWeblog(), urlStrategy));
            }
        } catch (Exception e) {
            log.error("ERROR: fetching weblog list", e);
        }
        return results;
    }
