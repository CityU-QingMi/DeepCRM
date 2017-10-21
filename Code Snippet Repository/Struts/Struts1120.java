    public void testActions() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-results.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        HashMap<String, String> parameters = new HashMap<>();
        HashMap<String, ResultConfig> results = new HashMap<>();

        results.put("chainDefaultTypedResult", new ResultConfig.Builder("chainDefaultTypedResult", ActionChainResult.class.getName()).build());

        results.put("mockTypedResult", new ResultConfig.Builder("mockTypedResult", MockResult.class.getName()).build());

        Map<String, String> resultParams = new HashMap<>();
        resultParams.put("actionName", "bar.vm");
        results.put("specificLocationResult", new ResultConfig.Builder("specificLocationResult", ActionChainResult.class.getName())
                .addParams(resultParams).build());

        resultParams = new HashMap<>();
        resultParams.put("actionName", "foo.vm");
        results.put("defaultLocationResult", new ResultConfig.Builder("defaultLocationResult", ActionChainResult.class.getName())
                .addParams(resultParams).build());

        resultParams = new HashMap<>();
        resultParams.put("foo", "bar");
        results.put("noDefaultLocationResult", new ResultConfig.Builder("noDefaultLocationResult", ActionChainResult.class.getName())
                .addParams(resultParams).build());

        ActionConfig expectedAction = new ActionConfig.Builder("default", "Bar", SimpleAction.class.getName())
            .addParams(parameters)
            .addResultConfigs(results)
            .build();

        // execute the configuration
        provider.init(configuration);
        provider.loadPackages();

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map<String, ActionConfig> actionConfigs = pkg.getActionConfigs();

        // assertions
        assertEquals(1, actionConfigs.size());

        ActionConfig action = actionConfigs.get("Bar");
        assertEquals(expectedAction, action);
    }
