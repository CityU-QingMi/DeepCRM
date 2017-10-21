    public void testParametersWithChineseInTheName() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("theProtectedMap['名字']", "test1");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        Map<String, String> existingMap = ((SimpleAction) proxy.getAction()).getTheProtectedMap();
        assertEquals(1, existingMap.size());
    }
