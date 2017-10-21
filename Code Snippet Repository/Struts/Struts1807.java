    public void testGetMappingWithSlashedNameAtRootButNoSlashPackage() throws Exception {

        req.setupGetRequestURI("/foo/actionName.action");
        req.setupGetServletPath("/foo/actionName.action");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");

        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setSlashesInActionNames("true");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("", mapping.getNamespace());
        assertEquals("foo/actionName", mapping.getName());
        assertNull(mapping.getMethod());
    }
