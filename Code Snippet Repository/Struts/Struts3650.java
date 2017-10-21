    public void testShouldBlockDynamicMethodInvocationAnsUseDestroy() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido!destroy");
        req.setServletPath("/animals/dog/fido!destroy");
        req.setMethod("DELETE");

        mapper.setAllowDynamicMethodCalls("false");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("fido", ((String[])mapping.getParams().get("id"))[0]);
        assertEquals("destroy", mapping.getMethod());
    }
