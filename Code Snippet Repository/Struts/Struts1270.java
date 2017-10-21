    public void testDoesNotAllowMethodInvocations() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("@java.lang.System@exit(1).dummy", "dumb value");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.MODEL_DRIVEN_PARAM_TEST, null, extraContext);
        assertEquals(Action.SUCCESS, proxy.execute());

        String property = System.getProperty("xwork.security.test");
        assertNull(property);
    }
