    public void testPackageDefaultClassRef() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-actions-packagedefaultclassref.xml";
        final String testDefaultClassName = "com.opensymphony.xwork2.UserSpecifiedDefaultAction";

        ConfigurationProvider provider = buildConfigurationProvider(filename);

        // setup expectations
        params.put("foo", "17");
        params.put("bar", "23");

        ActionConfig barWithPackageDefaultClassRefConfig =
                new ActionConfig.Builder("", "Bar", "")
                        .addParams(params)
                        .build();

        // execute the configuration
        provider.init(configuration);

        PackageConfig pkg = configuration.getPackageConfig("default");
        Map actionConfigs = pkg.getActionConfigs();

        // assertions
        assertEquals(1, actionConfigs.size());
        assertEquals(barWithPackageDefaultClassRefConfig, actionConfigs.get("Bar"));
    }
