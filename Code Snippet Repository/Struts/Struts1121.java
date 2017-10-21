    public void testResultInheritance() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-result-inheritance.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        // expectations
        provider.init(configuration);
        provider.loadPackages();

        // assertions
        PackageConfig subPkg = configuration.getPackageConfig("subPackage");
        assertEquals(1, subPkg.getResultTypeConfigs().size());
        assertEquals(3, subPkg.getAllResultTypeConfigs().size());
    }
