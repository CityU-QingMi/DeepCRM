    public void testCustomActionPrefix() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("foo:myAction", "");

        final StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setParameterMap(parameterMap);
        request.setupGetServletPath("/someServletPath.action");

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.addParameterAction("foo", new ParameterAction() {
            public void execute(String key, ActionMapping mapping) {
                mapping.setName("myAction");
            }
        });
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);

        assertEquals(actionMapping.getName(), "myAction");
    }
