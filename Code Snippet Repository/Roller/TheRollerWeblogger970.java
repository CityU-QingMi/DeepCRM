    public void testProperiesCRUD() throws Exception {
        
        // remember, the properties table is initialized during Roller startup
        PropertiesManager mgr = WebloggerFactory.getWeblogger().getPropertiesManager();
        TestUtils.endSession(true);
        
        RuntimeConfigProperty prop = null;
        
        // get a property by name
        prop = mgr.getProperty("site.name");
        assertNotNull(prop);
        
        // update a property
        prop.setValue("testtest");
        mgr.saveProperty(prop);
        TestUtils.endSession(true);
        
        // make sure property was updated
        prop = null;
        prop = mgr.getProperty("site.name");
        assertNotNull(prop);
        assertEquals("testtest", prop.getValue());
        
        // get all properties
        Map props = mgr.getProperties();
        assertNotNull(props);
        assertTrue(props.containsKey("site.name"));
        
        // update multiple properties
        prop = (RuntimeConfigProperty) props.get("site.name");
        prop.setValue("foofoo");
        prop = (RuntimeConfigProperty) props.get("site.description");
        prop.setValue("blahblah");
        mgr.saveProperties(props);
        TestUtils.endSession(true);
        
        // make sure all properties were updated
        props = mgr.getProperties();
        assertNotNull(props);
        assertEquals("foofoo", ((RuntimeConfigProperty)props.get("site.name")).getValue());
        assertEquals("blahblah", ((RuntimeConfigProperty)props.get("site.description")).getValue());
    }
