    public void queueApplicableAutoPings(WeblogEntry changedWeblogEntry) throws WebloggerException {
        if (PingConfig.getSuspendPingProcessing()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Ping processing is suspended." + " No auto pings will be queued.");
            }
            return;
        }

        PingQueueManager pingQueueMgr = roller.getPingQueueManager();
        List<AutoPing> applicableAutopings = getApplicableAutoPings(changedWeblogEntry);
        for (AutoPing autoPing : applicableAutopings) {
            pingQueueMgr.addQueueEntry(autoPing);
        }
    }
