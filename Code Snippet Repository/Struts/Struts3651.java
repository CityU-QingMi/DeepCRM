    public void testShouldBlockDynamicMethodInvocationAndUseUpdate() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido!update");
        req.setServletPath("/animals/dog/fido!update");
        req.setMethod("PUT");

        mapper.setAllowDynamicMethodCalls("false");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("fido", ((String[])mapping.getParams().get("id"))[0]);
        assertEquals("update", mapping.getMethod());
    }
