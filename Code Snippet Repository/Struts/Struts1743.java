    public void testInterceptorDestroy() throws Exception {           
        Mock mockInterceptor = new Mock(Interceptor.class);
        mockInterceptor.matchAndReturn("hashCode", 0);
        mockInterceptor.expect("destroy");
        
        InterceptorMapping interceptorMapping = new InterceptorMapping("test", (Interceptor) mockInterceptor.proxy());
        
        InterceptorStackConfig isc = new InterceptorStackConfig.Builder("test").addInterceptor(interceptorMapping).build();
        
        PackageConfig packageConfig = new PackageConfig.Builder("test").addInterceptorStackConfig(isc).build();
        
        Map<String, PackageConfig> packageConfigs = new HashMap<String, PackageConfig>();
        packageConfigs.put("test", packageConfig);

        Mock mockContainer = new Mock(Container.class);
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(ObjectFactory.class)), new ObjectFactory((Container) mockContainer.proxy()));
        String reloadConfigs = container.getInstance(String.class, XWorkConstants.RELOAD_XML_CONFIGURATION);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(String.class), C.eq(XWorkConstants.RELOAD_XML_CONFIGURATION)),
                reloadConfigs);

        Mock mockConfiguration = new Mock(Configuration.class);
        mockConfiguration.matchAndReturn("getPackageConfigs", packageConfigs);
        mockConfiguration.matchAndReturn("getContainer", mockContainer.proxy());
        mockConfiguration.expect("destroy");
        
        ConfigurationManager configurationManager = new ConfigurationManager(Container.DEFAULT_NAME);
        configurationManager.setConfiguration((Configuration) mockConfiguration.proxy());
        
        Dispatcher dispatcher = new MockDispatcher(new MockServletContext(), new HashMap<String, String>(), configurationManager);
        dispatcher.init();
        dispatcher.cleanup();
        
        mockInterceptor.verify();
        mockContainer.verify();
        mockConfiguration.verify();
    }
