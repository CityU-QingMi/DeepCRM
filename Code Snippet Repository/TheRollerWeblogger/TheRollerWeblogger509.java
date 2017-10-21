    public List<PlanetGroup> getGroups() {
        List list = new ArrayList<PlanetGroup>();
        try {
            PlanetManager planetManager = WebloggerFactory.getWeblogger().getPlanetManager();
            Planet defaultPlanet = planetManager.getWeblogger(DEFAULT_PLANET_HANDLE);
            Set<PlanetGroup> groups = defaultPlanet.getGroups();
            for (PlanetGroup group : groups) {
                // TODO needs pojo wrapping from planet
                list.add(group); 
            }
        } catch (Exception e) {
            log.error("ERROR: getting groups", e);
        }
        return list;        
    }
