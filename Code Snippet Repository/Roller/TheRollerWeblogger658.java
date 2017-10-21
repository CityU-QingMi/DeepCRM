    public void myPrepare() {

        // load list of ping targets
        loadPingTargets();

        // load specified ping target if possible
        if(!StringUtils.isEmpty(getPingTargetId())) {
            try {
                PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
                setPingTarget(pingTargetMgr.getPingTarget(getPingTargetId()));
            } catch (WebloggerException ex) {
                log.error("Error looking up ping target - " + getPingTargetId(), ex);
            }
        }
    }
