    public void testInterceptorsLoadedFromSpringApplicationContext() throws ConfigurationException {
        sac.registerSingleton("timer-interceptor", TimerInterceptor.class, new MutablePropertyValues());

        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-interceptors-spring.xml";

        // Expect a ConfigurationException to be thrown if the interceptor reference
        // cannot be resolved
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        // execute the configuration
        provider.init(configuration);
        provider.loadPackages();

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map interceptorConfigs = pkg.getInterceptorConfigs();

        // assertions for size
        assertEquals(1, interceptorConfigs.size());

        // assertions for interceptors
        InterceptorConfig seen = (InterceptorConfig) interceptorConfigs.get("timer");
        assertEquals("timer-interceptor", seen.getClassName());
    }
