    public void testResultTypes() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-results.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        // setup expectations
        ResultTypeConfig chainResult = new ResultTypeConfig.Builder("chain", ActionChainResult.class.getName()).build();
        ResultTypeConfig mockResult = new ResultTypeConfig.Builder("mock", MockResult.class.getName()).build();

        // execute the configuration
        provider.init(configuration);
        provider.loadPackages();

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map resultTypes = pkg.getResultTypeConfigs();

        // assertions
        assertEquals(2, resultTypes.size());
        assertEquals("chain", pkg.getDefaultResultType());
        assertEquals(chainResult, resultTypes.get("chain"));
        assertEquals(mockResult, resultTypes.get("mock"));
    }
