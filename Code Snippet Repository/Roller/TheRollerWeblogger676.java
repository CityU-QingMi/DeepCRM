    public String accept() {
        
        try {
            UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
            WeblogManager wmgr = WebloggerFactory.getWeblogger().getWeblogManager();
            Weblog weblog = wmgr.getWeblog(getInviteId());      
            umgr.confirmWeblogPermission(weblog, getAuthenticatedUser());
            WebloggerFactory.getWeblogger().flush();

        } catch (WebloggerException ex) {
            log.error("Error handling invitation accept weblog id - "+getInviteId(), ex);
            addError("yourWebsites.permNotFound");
        }
        
        return SUCCESS;
    }
