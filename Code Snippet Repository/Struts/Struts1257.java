    public void testNonexistentParametersGetLoggedInDevMode() throws Exception {
        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-test-beans.xml");
        container.inject(provider);
        loadConfigurationProviders(provider,
                new MockConfigurationProvider(Collections.singletonMap("devMode", "true")));
        Map<String, Object> params = new HashMap<>();
        params.put("not_a_property", "There is no action property named like this");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionConfig config = configuration.getRuntimeConfiguration().getActionConfig("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME);
        container.inject(config.getInterceptors().get(0).getInterceptor());
        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        final String actionMessage = "" + ((SimpleAction) proxy.getAction()).getActionMessages().toArray()[0];
        assertTrue(actionMessage.contains("Error setting expression 'not_a_property' with value 'There is no action property named like this'"));
    }
