    public void testXMLLoadCaching() {
        OValValidationManager manager = container.getInstance(OValValidationManager.class);
        List<Configurer> configurers = manager.getConfigurers(SimpleFieldsXML.class, "simpleFieldsXMLCaching", false);
        assertNotNull(configurers);
        assertEquals(2, configurers.size());

        //load again and check it is the same instance
        List<Configurer> configurers2 = manager.getConfigurers(SimpleFieldsXML.class, "simpleFieldsXMLCaching", false);
        assertNotNull(configurers2);
        assertEquals(2, configurers2.size());
        assertSame(configurers.get(0), configurers2.get(0));
    }
