    public static void teardownGroup(String id) throws Exception {

        // lookup
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        PlanetGroup group = mgr.getGroupById(id);

        // remove
        mgr.deleteGroup(group);
        group.getPlanet().getGroups().remove(group);

        // flush
        WebloggerFactory.getWeblogger().flush();
    }
