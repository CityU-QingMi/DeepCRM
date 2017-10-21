    public static Planet setupPlanet(String handle) throws Exception {

        Planet testPlanet = new Planet(handle, handle, handle);

        // store
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        mgr.savePlanet(testPlanet);

        // flush
        WebloggerFactory.getWeblogger().flush();

        // query to make sure we return the persisted object
        Planet planet = mgr.getWeblogger(handle);

        if (planet == null) {
            throw new WebloggerException("error inserting new planet");
        }

        return planet;
    }
