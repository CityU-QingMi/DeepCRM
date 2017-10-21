    public void testPostMapping() throws Exception {
        req.setRequestURI("/myapp/animals/dog");
        req.setServletPath("/animals/dog");
        req.setMethod("POST");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("create", mapping.getMethod());
    }
