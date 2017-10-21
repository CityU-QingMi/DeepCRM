    public void testInterceptorDefaultRefs() throws ConfigurationException {
        XmlConfigurationProvider provider = new XmlConfigurationProvider("com/opensymphony/xwork2/config/providers/xwork-test-interceptor-defaultref.xml");
        container.inject(provider);
        loadConfigurationProviders(provider);

        // expectations - the inherited interceptor stack
        // default package
        ArrayList<InterceptorMapping> interceptors = new ArrayList<>();
        interceptors.add(new InterceptorMapping("logging", objectFactory.buildInterceptor(loggingInterceptor, new HashMap<String, String>())));

        ActionConfig actionWithOwnRef = new ActionConfig.Builder("", "ActionWithOwnRef", SimpleAction.class.getName())
            .addInterceptors(interceptors)
            .build();

        ActionConfig actionWithDefaultRef = new ActionConfig.Builder("", "ActionWithDefaultRef", SimpleAction.class.getName())
            .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
            .build();

        // sub package
        // this should inherit
        ActionConfig actionWithNoRef = new ActionConfig.Builder("", "ActionWithNoRef", SimpleAction.class.getName())
            .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
            .build();

        interceptors = new ArrayList<>();
        interceptors.add(new InterceptorMapping("logging", objectFactory.buildInterceptor(loggingInterceptor, new HashMap<String, String>())));

        ActionConfig anotherActionWithOwnRef = new ActionConfig.Builder("", "AnotherActionWithOwnRef", SimpleAction.class.getName())
            .addInterceptor(new InterceptorMapping("logging", objectFactory.buildInterceptor(loggingInterceptor, new HashMap<String, String>())))
            .build();

        RuntimeConfiguration runtimeConfig = configurationManager.getConfiguration().getRuntimeConfiguration();

        // assertions
        assertEquals(actionWithOwnRef, runtimeConfig.getActionConfig("", "ActionWithOwnRef"));
        assertEquals(actionWithDefaultRef, runtimeConfig.getActionConfig("", "ActionWithDefaultRef"));

        assertEquals(actionWithNoRef, runtimeConfig.getActionConfig("", "ActionWithNoRef"));
        assertEquals(anotherActionWithOwnRef, runtimeConfig.getActionConfig("", "AnotherActionWithOwnRef"));
    }
