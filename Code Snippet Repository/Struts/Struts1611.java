    public void testAnnotatedMethodSuccess3() throws Exception {
        HashMap<String, Object> params = new HashMap<>();

        //make it not fail
        params.put("param1", "key1");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", "annotatedMethod", null, extraContext);
        proxy.execute();
        assertFalse(((ValidationAware) proxy.getAction()).hasActionErrors());
    }
