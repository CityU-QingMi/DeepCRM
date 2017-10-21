    public void testGetMappingWithNamespaceSlash() throws Exception {

        req.setupGetRequestURI("/my-hh/abc.action");
        req.setupGetServletPath("/my-hh/abc.action");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");

        DefaultActionMapper mapper = new DefaultActionMapper();
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("", mapping.getNamespace());
        assertEquals("abc", mapping.getName());

        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        mapper = new DefaultActionMapper();
        mapper.setSlashesInActionNames("true");
        mapping = mapper.getMapping(req, configManager);

        assertEquals("", mapping.getNamespace());
        assertEquals("my-hh/abc", mapping.getName());
    }
