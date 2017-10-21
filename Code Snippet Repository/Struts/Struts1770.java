    public void testGetUriWithSemicolonPresent() throws Exception {
        req.setupGetParameterMap(new HashMap());
        req.setupGetRequestURI("/my/namespace/actionName.action;abc=123rty56");
        req.setupGetServletPath("/my/namespace/actionName.action;abc=123rty56");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");

        DefaultActionMapper mapper = new DefaultActionMapper();
        ActionMapping mapping = mapper.getMapping(req, configManager);
        assertEquals("/my/namespace/actionName.action", mapper.getUriFromActionMapping(mapping));
    }
