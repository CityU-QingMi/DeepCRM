    public void testPlanetCRUD() throws Exception {
        
        // setup planet
        TestUtils.setupWeblogger();

        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        Planet testPlanet = new Planet("testPlanet", "testPlanet", "testPlanet");
        Planet planet = null;
        
        planet = mgr.getWeblogger("testPlanet");
        assertNull(planet);
        
        // add
        mgr.savePlanet(testPlanet);
        TestUtils.endSession(true);
        
        // verify
        planet = null;
        planet = mgr.getWebloggerById(testPlanet.getId());
        assertNotNull(planet);
        assertEquals("testPlanet", planet.getHandle());
        
        // modify
        planet.setTitle("foo");
        mgr.savePlanet(planet);
        TestUtils.endSession(true);
        
        // verify
        planet = null;
        planet = mgr.getWebloggerById(testPlanet.getId());
        assertNotNull(planet);
        assertEquals("foo", planet.getTitle());
        
        // remove
        mgr.deletePlanet(planet);
        TestUtils.endSession(true);
        
        // verify
        planet = null;
        planet = mgr.getWeblogger(testPlanet.getId());
        assertNull(planet);
    }
