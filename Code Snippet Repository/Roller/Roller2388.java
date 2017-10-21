    public String execute() {
        
        // if group blogging is disabled then you can't change permissions
        if (!WebloggerConfig.getBooleanProperty("groupblogging.enabled")) {
            addError("inviteMember.disabled");
            return SUCCESS;
        }
        
        log.debug("Showing weblog invitation form");
        
        return INPUT;
    }
