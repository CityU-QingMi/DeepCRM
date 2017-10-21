    public void myPrepare() {
        
        PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
        
        // load selected ping target, if possible
        if(getPingTargetId() != null) {
            try {
                setPingTarget(pingTargetMgr.getPingTarget(getPingTargetId()));
            } catch (WebloggerException ex) {
                log.error("Error looking up ping target - "+getPingTargetId(), ex);
            }
        }
        
        try {
            // load common ping targets list
            setCommonPingTargets(pingTargetMgr.getCommonPingTargets());
        } catch (WebloggerException ex) {
            log.error("Error loading ping target lists for weblog - "+getActionWeblog().getHandle(), ex);
            addError("Error loading ping targets");
        }
    }
