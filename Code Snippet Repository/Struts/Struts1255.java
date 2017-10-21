    public void testParametersOverwriteField() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("existingMap.boo", "This is blah");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        SimpleAction action = (SimpleAction) proxy.getAction();
        assertEquals(1, action.getTheExistingMap().size());
        assertNotNull(action.getTheExistingMap().get("boo"));
        assertNull(action.getTheExistingMap().get("existingKey"));
    }
