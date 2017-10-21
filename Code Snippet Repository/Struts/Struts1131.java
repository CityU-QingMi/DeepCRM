    public void testWildCardInclude() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-wildcard-include.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        provider.init(configuration);
        provider.loadPackages();

        PackageConfig defaultWildcardPackage = configuration.getPackageConfig("default-wildcard");
        assertNotNull(defaultWildcardPackage);
        assertEquals("default-wildcard", defaultWildcardPackage.getName());


        PackageConfig defaultOnePackage = configuration.getPackageConfig("default-1");
        assertNotNull(defaultOnePackage);
        assertEquals("default-1", defaultOnePackage.getName());
        
        PackageConfig defaultTwoPackage = configuration.getPackageConfig("default-2");
        assertNotNull(defaultTwoPackage);
        assertEquals("default-2", defaultTwoPackage.getName());       

        PackageConfig defaultPackage = configuration.getPackageConfig("default");
        assertNotNull(defaultPackage);
        assertEquals("default", defaultPackage.getName());

        configurationManager.addContainerProvider(provider);
        configurationManager.reload();

    }
