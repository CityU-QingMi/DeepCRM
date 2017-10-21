    public void testPlanetLookups() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        Planet planet = null;
        
        // by id
        planet = mgr.getWebloggerById(testPlanet.getId());
        assertNotNull(planet);
        assertEquals("planetFuncTest", planet.getHandle());
        
        // by handle
        planet = null;
        planet = mgr.getWeblogger("planetFuncTest");
        assertNotNull(planet);
        assertEquals("planetFuncTest", planet.getHandle());
        
        // all planets
        List planets = mgr.getWebloggers();
        assertNotNull(planets);
        assertEquals(1, planets.size());
    }
