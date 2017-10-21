    public static void teardownPlanet(String id) throws Exception {

        // lookup
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        Planet planet = mgr.getWebloggerById(id);

        // remove
        mgr.deletePlanet(planet);

        // flush
        WebloggerFactory.getWeblogger().flush();
    }
