    public void testDeleteRemoveWithFakeDelete() throws Exception {

        req.setupGetRequestURI("/my/namespace/bar/1/foo/2");
        req.setupGetServletPath("/my/namespace/bar/1/foo/2");
        req.setupAddParameter(Restful2ActionMapper.HTTP_METHOD_PARAM, "DELETE");
        req.setupAddParameter(Restful2ActionMapper.HTTP_METHOD_PARAM, "DELETE");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        req.setupGetMethod("POST");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/my/namespace", mapping.getNamespace());
        assertEquals("foo/2", mapping.getName());
        assertEquals("remove", mapping.getMethod());
        assertEquals(1, mapping.getParams().size());
        assertEquals("1", mapping.getParams().get("bar"));
    }
