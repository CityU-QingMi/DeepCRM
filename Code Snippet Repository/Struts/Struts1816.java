    public void testGetId() throws Exception {
        mapper.setIdParameterName("id");
        req.setupGetRequestURI("/my/namespace/foo/3");
        req.setupGetServletPath("/my/namespace/foo/3");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        req.setupGetMethod("GET");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/my/namespace", mapping.getNamespace());
        assertEquals("foo/3", mapping.getName());
        assertEquals("view", mapping.getMethod());
        assertEquals("3", mapping.getParams().get("id"));
    }
