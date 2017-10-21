    public void testGetJsessionIdSemicolonMappingWithMethodAllowDMI() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido!update;jsessionid=29fefpv23do1g");
        req.setServletPath("/animals/dog/fido");
        req.setMethod("GET");
        
        // allow DMI
        mapper.setAllowDynamicMethodCalls("true");
        
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("fido", ((String[]) mapping.getParams().get("id"))[0]);
        assertEquals("update", mapping.getMethod());
    }
