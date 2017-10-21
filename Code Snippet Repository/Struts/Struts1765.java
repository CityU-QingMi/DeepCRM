    public void testGetActionMappingAndUri1() throws Exception {
        ActionMapper mapper1 = new InnerActionMapper1();
        ActionMapper mapper2 = new InnerActionMapper2();
        ActionMapper mapper3 = new InnerActionMapper3();
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper1")), mapper1);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper2")), mapper3);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper3")), mapper2);
        CompositeActionMapper compositeActionMapper = new CompositeActionMapper((Container) mockContainer.proxy(), "mapper1,mapper2,mapper3");
        
        ActionMapping actionMapping = compositeActionMapper.getMapping(new MockHttpServletRequest(), new ConfigurationManager(Container.DEFAULT_NAME));
        String uri = compositeActionMapper.getUriFromActionMapping(new ActionMapping());
        mockContainer.verify();
        
        assertNotNull(actionMapping);
        assertNotNull(uri);
        assertTrue(actionMapping == InnerActionMapper3.actionMapping);
        assertTrue(uri == InnerActionMapper3.uri);
        
    }
