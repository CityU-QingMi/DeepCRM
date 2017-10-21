    public String disable() {
        
        if(getPingTarget() != null) {
            try {
                getPingTarget().setAutoEnabled(false);
                
                PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
                pingTargetMgr.savePingTarget(getPingTarget());
                WebloggerFactory.getWeblogger().flush();
            } catch (Exception ex) {
                log.error("Error saving ping target", ex);
                addError("commonPingTargets.error.saving");
            }
        } else {
            addError("commonPingTargets.error.disabling");
        }
        
        return LIST;
    }
