    public User getUser() {
        
        if(user == null && authenticUser != null) {
            try {
                UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
                user = umgr.getUserByUserName(authenticUser);
            } catch (WebloggerException ex) {
                log.error("Error looking up user "+authenticUser, ex);
            }
        }
        
        return user;
    }
