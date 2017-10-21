    public void testRedirectPrefix() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("redirect:" + "http://www.google.com", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setupGetServletPath("/someServletPath.action");
        request.setParameterMap(parameterMap);

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setContainer(container);
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        Result result = actionMapping.getResult();
        assertNull(result);
    }
