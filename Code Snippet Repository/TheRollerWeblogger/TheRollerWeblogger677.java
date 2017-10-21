    public String decline() {
        
        try {
            UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
            WeblogManager wmgr = WebloggerFactory.getWeblogger().getWeblogManager();
            Weblog weblog = wmgr.getWeblog(getInviteId());
            String handle = weblog.getHandle();                       
            // TODO ROLLER_2.0: notify inviter that invitee has declined invitation
            // TODO EXCEPTIONS: better exception handling here
            umgr.declineWeblogPermission(weblog, getAuthenticatedUser());
            WebloggerFactory.getWeblogger().flush();
            addMessage("yourWebsites.declined", handle);

        } catch (WebloggerException ex) {
            log.error("Error handling invitation decline weblog id - "+getInviteId(), ex);
            addError("yourWebsites.permNotFound");
        }
        
        return SUCCESS;
    }
