    public String disable() {
        
        if(getPingTarget() != null) {
            try {
                AutoPingManager autoPingMgr = WebloggerFactory.getWeblogger().getAutopingManager();
                autoPingMgr.removeAutoPing(getPingTarget(), getActionWeblog());
                WebloggerFactory.getWeblogger().flush();
            } catch (Exception ex) {
                log.error("Error removing auto ping for target - "+getPingTargetId(), ex);
                addError("Error disabling auto ping");
            }
        }
        
        return execute();
    }
