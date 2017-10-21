    public void testDefaultActionClass() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-actions.xml";
        final String testDefaultClassName = "com.opensymphony.xwork2.ActionSupport";

        ConfigurationProvider provider = buildConfigurationProvider(filename);

        // setup expectations
        params.put("foo", "17");
        params.put("bar", "23");

        ActionConfig barWithoutClassNameConfig =
                new ActionConfig.Builder("", "BarWithoutClassName", "")
                        .addParams(params)
                        .build();

        // execute the configuration
        provider.init(configuration);

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map actionConfigs = pkg.getActionConfigs();

        // assertions
        assertEquals(7, actionConfigs.size());
        assertEquals(barWithoutClassNameConfig, actionConfigs.get("BarWithoutClassName"));
    }
