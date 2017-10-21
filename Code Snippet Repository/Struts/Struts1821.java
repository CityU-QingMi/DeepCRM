    public void testPutUpdateWithIdParam() throws Exception {

        mapper.setIdParameterName("id");
        req.setupGetRequestURI("/my/namespace/bar/1/foo/2");
        req.setupGetServletPath("/my/namespace/bar/1/foo/2");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");
        req.setupGetMethod("PUT");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/my/namespace", mapping.getNamespace());
        assertEquals("foo", mapping.getName());
        assertEquals("update", mapping.getMethod());
        assertEquals(2, mapping.getParams().size());
        assertEquals("1", mapping.getParams().get("bar"));
        assertEquals("2", mapping.getParams().get("id"));
        
    }
