    public void testGetIndex() throws Exception {
        req.setupGetRequestURI("/my/namespace/foo/");
        req.setupGetServletPath("/my/namespace/foo/");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        req.setupGetMethod("GET");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/my/namespace", mapping.getNamespace());
        assertEquals("foo/", mapping.getName());
        assertEquals("index", mapping.getMethod());
    }
