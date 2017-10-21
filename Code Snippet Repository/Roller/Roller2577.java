    public void testGroupLookups() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        // lookup group by id
        PlanetGroup group = mgr.getGroupById(testGroup1.getId());
        assertNotNull(group);
        assertEquals("groupFuncTest1", group.getHandle());
        
        // lookup group by planet & handle
        group = null;
        group = mgr.getGroup(testPlanet, testGroup1.getHandle());
        assertNotNull(group);
        assertEquals("groupFuncTest1", group.getHandle());
        
        // lookup all groups in planet
        Planet planet = mgr.getWebloggerById(testPlanet.getId());
        Set groups = planet.getGroups();
        assertNotNull(groups);
        assertEquals(2, groups.size());
    }
