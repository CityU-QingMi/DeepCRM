    private void buildIsEnabledMap() {
        
        AutoPingManager autoPingMgr = WebloggerFactory.getWeblogger().getAutopingManager();
        
        // Build isEnabled map (keyed by ping target id and values Boolean.TRUE/Boolean.FALSE)
        Map<String, Boolean> isEnabled = new HashMap<String, Boolean>();
        
        List<AutoPing> autoPings = Collections.emptyList();
        try {
            autoPings = autoPingMgr.getAutoPingsByWebsite(getActionWeblog());
        } catch (WebloggerException ex) {
            log.error("Error looking up auto pings for weblog - "+getActionWeblog().getHandle(), ex);
        }
        
        // Add the enabled auto ping configs with TRUE
        for (AutoPing autoPing : autoPings) {
            isEnabled.put(autoPing.getPingTarget().getId(), Boolean.TRUE);
        }
        
        // Add disabled ping targets ones with FALSE
        for (PingTarget inPingTarget : getCommonPingTargets()) {
            if (isEnabled.get(inPingTarget.getId()) == null) {
                isEnabled.put(inPingTarget.getId(), Boolean.FALSE);
            }
        }

        if (isEnabled.size() > 0) {
            setPingStatus(isEnabled);
        }
    }
