    public void testGroupCRUD() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        PlanetGroup testGroup = new PlanetGroup();
        testGroup.setDescription("test_group_desc");
        testGroup.setHandle("test_handle");
        testGroup.setTitle("test_title");
        testGroup.setPlanet(testPlanet);
        PlanetGroup group = null;
        
        group = mgr.getGroup(testPlanet, "test_handle");
        assertNull(group);
        
        // add
        mgr.saveGroup(testGroup);
        TestUtils.endSession(true);
        
        // verify
        group = null;
        group = mgr.getGroupById(testGroup.getId());
        assertNotNull(group);
        assertEquals("test_handle", group.getHandle());
        assertEquals(testPlanet.getId(), group.getPlanet().getId());
        
        // modify
        group.setTitle("foo");
        mgr.saveGroup(group);
        TestUtils.endSession(true);
        
        // verify
        group = null;
        group = mgr.getGroupById(testGroup.getId());
        assertNotNull(group);
        assertEquals("foo", group.getTitle());
        
        // remove
        mgr.deleteGroup(group);
        TestUtils.endSession(true);
        
        // verify
        group = null;
        group = mgr.getGroupById(testGroup.getId());
        assertNull(group);
    }
