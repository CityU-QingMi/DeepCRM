    public void testBasicPackages() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-basic-packages.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);
        provider.init(configuration);
        provider.loadPackages();

        // setup our expectations
        PackageConfig expectedNamespacePackage = new PackageConfig.Builder("namespacepkg")
            .namespace("/namespace/set")
            .isAbstract(false)
            .build();
        PackageConfig expectedAbstractPackage = new PackageConfig.Builder("abstractpkg")
            .isAbstract(true)
            .build();

        // test expectations
        assertEquals(3, configuration.getPackageConfigs().size());
        assertEquals(expectedNamespacePackage, configuration.getPackageConfig("namespacepkg"));
        assertEquals(expectedAbstractPackage, configuration.getPackageConfig("abstractpkg"));
    }
