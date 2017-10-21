    public void testActionPrefix_fromImageButton() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put(DefaultActionMapper.ACTION_PREFIX + "myAction", "");
        parameterMap.put(DefaultActionMapper.ACTION_PREFIX + "myAction.x", "");
        parameterMap.put(DefaultActionMapper.ACTION_PREFIX + "myAction.y", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setParameterMap(parameterMap);
        request.setupGetServletPath("/someServletPath.action");

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setAllowActionPrefix("true");
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        assertEquals("myAction", actionMapping.getName());
    }
