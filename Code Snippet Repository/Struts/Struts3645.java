    public void testMappingWithMethodAndId() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido/test/some-id!create;jsessionid=29fefpv23do1g");
        req.setServletPath("/animals/dog/fido/test/some-id");
        req.setMethod("GET");
        mapper.setAllowDynamicMethodCalls("true");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog/fido/test", mapping.getName());
        assertEquals("some-id", ((String[]) mapping.getParams().get("id"))[0]);
        assertEquals("create", mapping.getMethod());
    }
