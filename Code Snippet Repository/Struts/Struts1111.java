    public void testInterceptorInheritance() throws ConfigurationException {
        
        // expectations - the inherited interceptor stack
        InterceptorStackConfig inheritedStack = new InterceptorStackConfig.Builder("subDefaultStack")
            .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
            .build();

        ConfigurationProvider provider = buildConfigurationProvider("com/opensymphony/xwork2/config/providers/xwork-test-interceptor-inheritance.xml");

        // assertions
        PackageConfig defaultPkg = configuration.getPackageConfig("default");
        assertEquals(2, defaultPkg.getInterceptorConfigs().size());

        PackageConfig subPkg = configuration.getPackageConfig("subPackage");
        assertEquals(1, subPkg.getInterceptorConfigs().size());
        assertEquals(3, subPkg.getAllInterceptorConfigs().size());
        assertEquals(inheritedStack, subPkg.getInterceptorConfigs().get("subDefaultStack"));

        // expectations - the inherited interceptor stack
        inheritedStack = new InterceptorStackConfig.Builder("subSubDefaultStack")
                .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
                .addInterceptor(new InterceptorMapping("timer", objectFactory.buildInterceptor(timerInterceptor, new HashMap<String, String>())))
                .build();

        PackageConfig subSubPkg = configuration.getPackageConfig("subSubPackage");
        assertEquals(1, subSubPkg.getInterceptorConfigs().size());
        assertEquals(4, subSubPkg.getAllInterceptorConfigs().size());
        assertEquals(inheritedStack, subSubPkg.getInterceptorConfigs().get("subSubDefaultStack"));
    }
