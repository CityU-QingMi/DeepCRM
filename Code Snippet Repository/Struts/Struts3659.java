    public void testPutMapping() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido");
        req.setServletPath("/animals/dog/fido");
        req.setMethod("PUT");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("update", mapping.getMethod());
        assertEquals("fido", ((String[]) mapping.getParams().get("id"))[0]);
    }
