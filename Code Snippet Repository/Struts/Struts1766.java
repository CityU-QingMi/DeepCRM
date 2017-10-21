    public void testGetActionMappingAndUri2() throws Exception {
        ActionMapper mapper1 = new InnerActionMapper1();
        ActionMapper mapper2 = new InnerActionMapper2();
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper1")), mapper1);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper2")), mapper2);
        CompositeActionMapper compositeActionMapper = new CompositeActionMapper((Container) mockContainer.proxy(), "mapper1,mapper2");

        ActionMapping actionMapping = compositeActionMapper.getMapping(new MockHttpServletRequest(), new ConfigurationManager(Container.DEFAULT_NAME));
        String uri = compositeActionMapper.getUriFromActionMapping(new ActionMapping());
        mockContainer.verify();

        assertNull(actionMapping);
        assertNull(uri);
    }
