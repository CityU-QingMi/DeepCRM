    public void updateSubscriptions(PlanetGroup group) throws UpdaterException {
        
        if(group == null) {
            throw new IllegalArgumentException("cannot update null group");
        }
        
        updateProxySettings();
        
        log.debug("--- BEGIN --- Updating subscriptions in group = "+group.getHandle());
        
        long startTime = System.currentTimeMillis();
        
        updateSubscriptions(group.getSubscriptions());
        
        long endTime = System.currentTimeMillis();
        log.info("--- DONE --- Updated subscriptions in "
                + ((endTime-startTime) / RollerConstants.SEC_IN_MS) + " seconds");
    }
