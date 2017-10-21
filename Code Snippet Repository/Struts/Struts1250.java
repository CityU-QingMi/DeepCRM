    public void testExcludedTrickyParameters() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>() {
            {
                put("blah", "This is blah");
                put("name", "try_1");
                put("(name)", "try_2");
                put("['name']", "try_3");
                put("['na' + 'me']", "try_4");
                put("{name}[0]", "try_5");
                put("(new string{'name'})[0]", "try_6");
                put("#{key: 'name'}.key", "try_7");

            }
        };

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);

        ActionConfig config = configuration.getRuntimeConfiguration().getActionConfig("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME);
        ParametersInterceptor pi = (ParametersInterceptor) config.getInterceptors().get(0).getInterceptor();
        pi.setExcludeParams("name");

        proxy.execute();

        SimpleAction action = (SimpleAction) proxy.getAction();
        assertNull(action.getName());
        assertEquals("This is blah", (action).getBlah());
    }
