    public String enable() {
        
        if(getPingTarget() != null) {
            try {
                AutoPingManager autoPingMgr = WebloggerFactory.getWeblogger().getAutopingManager();
                AutoPing autoPing = new AutoPing(null, getPingTarget(), getActionWeblog());
                autoPingMgr.saveAutoPing(autoPing);
                WebloggerFactory.getWeblogger().flush();
            } catch(Exception ex) {
                log.error("Error saving auto ping for target - "+getPingTargetId(), ex);
                addError("Error enabling auto ping");
            }
        }
        
        return execute();
    }
