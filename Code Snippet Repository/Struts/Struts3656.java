    public void testDynamicMethodInvocation() throws Exception {
        // given
        req.setRemoteAddr("/myapp/animals/dog/23!edit");
        req.setServletPath("/animals/dog/23!edit");
        req.setMethod("GET");

        mapper.setAllowDynamicMethodCalls("true");

        // when
        ActionMapping actionMapping = mapper.getMapping(req, configManager);

        // then
        assertEquals("dog", actionMapping.getName());
        assertEquals("edit", actionMapping.getMethod());
        assertEquals("/animals", actionMapping.getNamespace());
        assertEquals("23", ((String[]) actionMapping.getParams().get("id"))[0]);
    }
