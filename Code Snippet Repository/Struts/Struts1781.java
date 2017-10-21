    public void testActionPrefixWhenSlashesButSlashesDisabledAndCrossNamespaceDisabled() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put(DefaultActionMapper.ACTION_PREFIX + "my/Action", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setParameterMap(parameterMap);
        request.setupGetServletPath("/someServletPath.action");

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setAllowActionPrefix("true");
        defaultActionMapper.setSlashesInActionNames("false");
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        assertEquals("Action", actionMapping.getName());
    }
