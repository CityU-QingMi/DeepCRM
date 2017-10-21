    public void testGetMappingWithUnknownNamespaceButFullNamespaceSelect() throws Exception {
        req.setupGetRequestURI("/bo/foo/actionName.action");
        req.setupGetServletPath("/bo/foo/actionName.action");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");

        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setAlwaysSelectFullNamespace("true");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/bo/foo", mapping.getNamespace());
        assertEquals("actionName", mapping.getName());
        assertNull(mapping.getMethod());
    }
