    public void testPutUpdate() throws Exception {

        req.setupGetRequestURI("/my/namespace/bar/1/foo/2");
        req.setupGetServletPath("/my/namespace/bar/1/foo/2");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        req.setupGetMethod("PUT");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/my/namespace", mapping.getNamespace());
        assertEquals("foo/2", mapping.getName());
        assertEquals("update", mapping.getMethod());
        assertEquals(1, mapping.getParams().size());
        assertEquals("1", mapping.getParams().get("bar"));
    }
