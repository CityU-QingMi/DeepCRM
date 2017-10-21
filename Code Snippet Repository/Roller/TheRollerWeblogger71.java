    public static PlanetGroup setupGroup(Planet planet, String handle)
            throws Exception {

        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();

        // make sure we are using a persistent object
        Planet testPlanet = mgr.getWebloggerById(planet.getId());

        // store
        PlanetGroup testGroup = new PlanetGroup(testPlanet, handle, handle,
                handle);
        testPlanet.getGroups().add(testGroup);
        mgr.saveGroup(testGroup);

        // flush
        WebloggerFactory.getWeblogger().flush();

        // query to make sure we return the persisted object
        PlanetGroup group = mgr.getGroupById(testGroup.getId());

        if (group == null) {
            throw new WebloggerException("error inserting new group");
        }

        return group;
    }
