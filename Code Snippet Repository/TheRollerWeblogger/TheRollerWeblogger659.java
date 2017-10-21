    public String enable() {
        
        if(getPingTarget() != null) {
            try {
                getPingTarget().setAutoEnabled(true);
                
                PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
                pingTargetMgr.savePingTarget(getPingTarget());
                WebloggerFactory.getWeblogger().flush();
            } catch (Exception ex) {
                log.error("Error saving ping target", ex);
                addError("commonPingTargets.error.saving");
            }
        } else {
            addError("commonPingTargets.error.enabling");
        }
        
        return LIST;
    }
