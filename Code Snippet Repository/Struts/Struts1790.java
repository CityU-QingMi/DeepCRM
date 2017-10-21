    public void testUnsafeRedirectActionPrefix() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("redirectAction:" + "%{3*4}", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setupGetServletPath("/someServletPath.action");
        request.setParameterMap(parameterMap);

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setContainer(container);
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);


        StrutsResultSupport result = (StrutsResultSupport) actionMapping.getResult();
        assertNull(result);
    }
