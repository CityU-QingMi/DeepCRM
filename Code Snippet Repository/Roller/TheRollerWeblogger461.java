    @Override
    public boolean isUserInRole(String roleName) {
        UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
        if (getUserPrincipal() != null) {
            try {
                User user = umgr.getUserByUserName(getUserPrincipal().getName(), Boolean.TRUE);
                return umgr.hasRole(roleName, user);
            } catch (WebloggerException ex) {
                log.error("ERROR checking user rile", ex);
            }
        }
        return false;
    }
