    public void testUnsafeRedirectPrefix() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("redirect:" + "http://%{3*4}", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setupGetServletPath("/someServletPath.action");
        request.setParameterMap(parameterMap);

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setContainer(container);
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        Result result = actionMapping.getResult();
        assertNull(result);
    }
