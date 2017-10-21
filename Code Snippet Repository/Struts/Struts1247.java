    public void testParametersWithSpacesInTheName() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("theProtectedMap['p0 p1']", "test1");
        params.put("theProtectedMap['p0p1 ']", "test2");
        params.put("theProtectedMap[' p0p1 ']", "test3");
        params.put("theProtectedMap[' p0 p1 ']", "test4");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        Map<String, String> existingMap = ((SimpleAction) proxy.getAction()).getTheProtectedMap();
        assertEquals(0, existingMap.size());
    }
