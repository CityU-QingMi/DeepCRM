    public void testActionPrefixWhenSlashesAndCrossNamespaceDisabled() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put(DefaultActionMapper.ACTION_PREFIX + "my/Action", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setParameterMap(parameterMap);
        request.setupGetServletPath("/someServletPath.action");

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setAllowActionPrefix("true");
        defaultActionMapper.setSlashesInActionNames("true");
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        assertEquals("my/Action", actionMapping.getName());
    }
