    public void testNewMapping() throws Exception {
        req.setRequestURI("/myapp/animals/dog/new");
        req.setServletPath("/animals/dog/new");
        req.setMethod("GET");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("editNew", mapping.getMethod());
    }
