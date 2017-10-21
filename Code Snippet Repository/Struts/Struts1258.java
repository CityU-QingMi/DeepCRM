    public void testNonexistentParametersAreIgnoredInProductionMode() throws Exception {
        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-test-beans.xml");
        container.inject(provider);
        loadConfigurationProviders(provider,
                new MockConfigurationProvider(Collections.singletonMap("devMode", "false")));
        Map<String, Object> params = new HashMap<>();
        params.put("not_a_property", "There is no action property named like this");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionConfig config = configuration.getRuntimeConfiguration().getActionConfig("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME);
        container.inject(config.getInterceptors().get(0).getInterceptor());
        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        assertTrue(((SimpleAction) proxy.getAction()).getActionMessages().isEmpty());
    }
