    public void testExpressionValidatorSuccess() throws Exception {
        HashMap<String, Object> params = new HashMap<>();

        //make it not fail
        params.put("date", "12/23/2002");
        params.put("foo", "10");
        params.put("bar", "7");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, extraContext);
        proxy.execute();
        assertFalse(((ValidationAware) proxy.getAction()).hasActionErrors());
    }
