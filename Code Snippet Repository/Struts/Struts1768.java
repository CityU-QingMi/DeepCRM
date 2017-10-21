    public void testGetMappingWithActionName_noDynamicMethodColonPrefix() throws Exception {

        Map parameterMap = new HashMap();
        parameterMap.put(DefaultActionMapper.METHOD_PREFIX + "someMethod", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setParameterMap(parameterMap);
        request.setupGetServletPath("/someServletPath.action");

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setAllowDynamicMethodCalls("false");
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        assertEquals("someServletPath", actionMapping.getName());
        assertEquals(null, actionMapping.getMethod());
    }
