    public void testGetJsessionIdSemicolonMappingWithMethod() throws Exception {
        req.setRequestURI("/myapp/animals/dog/fido!update;jsessionid=29fefpv23do1g");
        req.setServletPath("/animals/dog/fido");
        req.setMethod("GET");
        
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/animals", mapping.getNamespace());
        assertEquals("dog", mapping.getName());
        assertEquals("fido", ((String[]) mapping.getParams().get("id"))[0]);
        assertEquals("show", mapping.getMethod());
    }
