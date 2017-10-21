    public void testActions() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-exception-mappings.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        List<ExceptionMappingConfig> exceptionMappings = new ArrayList<>();
        HashMap<String, String> parameters = new HashMap<>();
        HashMap<String, ResultConfig> results = new HashMap<>();

        exceptionMappings.add(
                new ExceptionMappingConfig.Builder("spooky-result", "com.opensymphony.xwork2.SpookyException", "spooky-result")
                        .build());
        results.put("spooky-result", new ResultConfig.Builder("spooky-result", MockResult.class.getName()).build());

        Map<String, String> resultParams = new HashMap<>();
        resultParams.put("actionName", "bar.vm");
        results.put("specificLocationResult",
                new ResultConfig.Builder("specificLocationResult", ActionChainResult.class.getName())
                        .addParams(resultParams)
                        .build());

        ActionConfig expectedAction = new ActionConfig.Builder("default", "Bar", SimpleAction.class.getName())
            .addParams(parameters)
            .addResultConfigs(results)
            .addExceptionMappings(exceptionMappings)
            .build();

        // execute the configuration
        provider.init(configuration);
        provider.loadPackages();

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map actionConfigs = pkg.getActionConfigs();

        // assertions
        assertEquals(1, actionConfigs.size());

        ActionConfig action = (ActionConfig) actionConfigs.get("Bar");
        assertEquals(expectedAction, action);
    }
