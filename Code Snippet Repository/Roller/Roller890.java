    public void updateSubscriptions() throws UpdaterException {
        
        updateProxySettings();
        
        log.debug("--- BEGIN --- Updating all subscriptions");
        
        long startTime = System.currentTimeMillis();
        
        try {
            // update all subscriptions in the system
            PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
            updateSubscriptions(pmgr.getSubscriptions());
        } catch (RollerException ex) {
            throw new UpdaterException("Error getting subscriptions list", ex);
        }
        
        long endTime = System.currentTimeMillis();
        log.info("--- DONE --- Updated subscriptions in "
                + ((endTime-startTime) / RollerConstants.SEC_IN_MS) + " seconds");
    }
