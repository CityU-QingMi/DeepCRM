    public void testDefaultPackage() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-default-package.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);
        provider.init(configuration);
        provider.loadPackages();

        // setup our expectations
        PackageConfig expectedPackageConfig = new PackageConfig.Builder("default").build();

        // test expectations
        assertEquals(1, configuration.getPackageConfigs().size());
        assertEquals(expectedPackageConfig, configuration.getPackageConfig("default"));
    }
