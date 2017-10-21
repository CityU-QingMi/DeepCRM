    public void testParametersNotAccessPrivateVariables() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("protectedMap.foo", "This is blah");
        params.put("theProtectedMap.boo", "This is blah");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        SimpleAction action = (SimpleAction) proxy.getAction();
        assertEquals(1, action.getTheProtectedMap().size());
        assertNotNull(action.getTheProtectedMap().get("boo"));
        assertNull(action.getTheProtectedMap().get("foo"));
    }
