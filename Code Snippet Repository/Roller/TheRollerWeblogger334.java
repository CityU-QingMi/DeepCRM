    @Override
    public void myPrepare() {
        
        if(getPlanet() != null && getBean().getId() != null) {
            try {
                PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
                setGroup(pmgr.getGroupById(getBean().getId()));
            } catch(Exception ex) {
                log.error("Error looking up planet group - " + getBean().getId(), ex);
            }
        }
    }
