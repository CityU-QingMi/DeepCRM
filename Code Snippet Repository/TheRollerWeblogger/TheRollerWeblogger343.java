    public Planet getPlanet() {
        if(planet == null) {
            try {
                PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
                planet = pmgr.getWeblogger(DEFAULT_PLANET_HANDLE);
            } catch(Exception ex) {
                log.error("Error loading weblogger planet - "+DEFAULT_PLANET_HANDLE, ex);
            }
        }
        return planet;
    }
