    public List getWeblogsUsers(String handle) {
        List results = new ArrayList();
        try {            
            Weblogger roller = WebloggerFactory.getWeblogger();
            UserManager umgr = roller.getUserManager();
            Weblog website = WebloggerFactory.getWeblogger().getWeblogManager().getWeblogByHandle(handle);
            List<WeblogPermission> perms = umgr.getWeblogPermissions(website);
            for (WeblogPermission perm : perms) {
                results.add(UserWrapper.wrap(perm.getUser()));
            }
        } catch (Exception e) {
            log.error("ERROR: fetching weblog list", e);
        }
        return results;
    }
