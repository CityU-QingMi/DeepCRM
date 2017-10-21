    public void testPutContinueMapping() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido");
        req.setServletPath("/animals/dog/fido");
        req.setMethod("PUT");
        req.addHeader("Expect", "100-continue");

        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("updateContinue", mapping.getMethod());
        assertEquals("fido", ((String[]) mapping.getParams().get("id"))[0]);
    }
