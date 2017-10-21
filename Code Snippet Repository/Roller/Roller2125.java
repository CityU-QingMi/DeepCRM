    public PlanetGroup getGroup(String groupHandle) {
        PlanetGroup group = null;
        try {
            PlanetManager planetManager = WebloggerFactory.getWeblogger().getPlanetManager();
            Planet defaultPlanet = planetManager.getWeblogger(DEFAULT_PLANET_HANDLE);            
            // TODO needs pojo wrapping from planet
            group = planetManager.getGroup(defaultPlanet, groupHandle);            
        } catch (Exception e) {
            log.error("ERROR: getting group", e);
        }
        return group;        
    }
