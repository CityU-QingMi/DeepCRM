    @Override
    public void myPrepare() {
        
        PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        // lookup group we are operating on, if none specified then use default
        if (getGroupHandle() == null) {
            setGroupHandle("all");
        }
        
        try {
            setGroup(pmgr.getGroup(getPlanet(), getGroupHandle()));
        } catch (RollerException ex) {
            LOGGER.error("Error looking up planet group - " + getGroupHandle(), ex);
        }
    }
