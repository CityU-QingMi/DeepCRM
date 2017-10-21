    public User getAuthenticatedUser() {
        
        User authenticUser = null;
        if(userName != null) {
            try {
                UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
                authenticUser = mgr.getUserByUserName(userName);
            } catch (WebloggerException ex) {
                log.warn("Error looking up authenticated user "+userName, ex);
            }
        }
        
        return authenticUser;
    }
