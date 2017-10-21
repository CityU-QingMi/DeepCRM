    public void testBasicInterceptors() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-interceptors-basic.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        // setup expectations
        // the test interceptor with a parameter
        Map<String, String> params = new HashMap<>();
        params.put("foo", "expectedFoo");

        InterceptorConfig paramsInterceptor = new InterceptorConfig.Builder("test", MockInterceptor.class.getName())
            .addParams(params).build();

        // the default interceptor stack
        InterceptorStackConfig defaultStack = new InterceptorStackConfig.Builder("defaultStack")
                .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
                .addInterceptor(new InterceptorMapping("test", objectFactory.buildInterceptor(mockInterceptor, params)))
                .build();

        // the derivative interceptor stack
        InterceptorStackConfig derivativeStack = new InterceptorStackConfig.Builder("derivativeStack")
                .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
            .addInterceptor(new InterceptorMapping("test", objectFactory.buildInterceptor(mockInterceptor, params)))
                .addInterceptor(new InterceptorMapping("logging", objectFactory.buildInterceptor(loggingInterceptor, new HashMap<String, String>())))
            .build();

        // execute the configuration
        provider.init(configuration);
        provider.loadPackages();

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map interceptorConfigs = pkg.getInterceptorConfigs();

        // assertions for size
        assertEquals(5, interceptorConfigs.size());

        // assertions for interceptors
        assertEquals(timerInterceptor, interceptorConfigs.get("timer"));
        assertEquals(loggingInterceptor, interceptorConfigs.get("logging"));
        assertEquals(paramsInterceptor, interceptorConfigs.get("test"));

        // assertions for interceptor stacks
        assertEquals(defaultStack, interceptorConfigs.get("defaultStack"));
        assertEquals(derivativeStack, interceptorConfigs.get("derivativeStack"));
    }
