    public void testShouldBlockDynamicMethodInvocationAnsUseShow() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido!edit");
        req.setServletPath("/animals/dog/fido!edit");
        req.setMethod("GET");

        mapper.setAllowDynamicMethodCalls("false");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("fido", ((String[])mapping.getParams().get("id"))[0]);
        assertEquals("show", mapping.getMethod());
    }
