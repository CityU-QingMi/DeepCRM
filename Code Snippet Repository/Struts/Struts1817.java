    public void testGetEdit() throws Exception {
        mapper.setIdParameterName("id");
        mapper.setAllowDynamicMethodCalls("true");
        req.setupGetRequestURI("/my/namespace/foo/3!edit");
        req.setupGetServletPath("/my/namespace/foo/3!edit");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        req.setupGetMethod("GET");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/my/namespace", mapping.getNamespace());
        assertEquals("foo/3", mapping.getName());
        assertEquals("edit", mapping.getMethod());
        assertEquals("3", mapping.getParams().get("id"));
    }
