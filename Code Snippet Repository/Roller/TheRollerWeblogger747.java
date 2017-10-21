    public String resign() {
        try {
            UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
            // TODO: notify website members that user has resigned
            // TODO EXCEPTIONS: better exception handling
            umgr.revokeWeblogPermission(getActionWeblog(), getAuthenticatedUser(), WeblogPermission.ALL_ACTIONS);
            WebloggerFactory.getWeblogger().flush();
            addMessage("yourWebsites.resigned", getWeblog());
        } catch (WebloggerException ex) {
            log.error("Error doing weblog resign - " + getActionWeblog().getHandle(), ex);
            addError("Resignation failed - check system logs");
        }
        return SUCCESS;
    }
