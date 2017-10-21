    public void testPostContinueMapping() throws Exception {
        req.setRequestURI("/myapp/animals/dog");
        req.setServletPath("/animals/dog");
        req.setMethod("POST");
        req.addHeader("Expect", "100-continue");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("createContinue", mapping.getMethod());
    }
