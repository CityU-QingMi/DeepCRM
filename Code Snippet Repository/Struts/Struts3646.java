    public void testMappingForStaticFiles() throws Exception {
        req.setRequestURI("/myApp/custom/menu/Yosemite/Vernal_Fall/Vernal_Fall_Image!iframe");
        req.setServletPath("/custom/menu/Yosemite/Vernal_Fall/Vernal_Fall_Image");
        req.setMethod("GET");
        mapper.setAllowDynamicMethodCalls("true");
        final ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("", mapping.getNamespace());
        assertEquals("custom/menu/Yosemite/Vernal_Fall", mapping.getName());
        assertEquals("Vernal_Fall_Image", ((String[]) mapping.getParams().get("id"))[0]);
        assertEquals("iframe", mapping.getMethod());
    }
