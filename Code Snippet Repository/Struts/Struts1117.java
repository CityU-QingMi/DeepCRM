    public void testDefaultClassRef() throws ConfigurationException {
    	final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-defaultclassref-package.xml";
        final String hasDefaultClassRefPkgName = "hasDefaultClassRef";
        final String noDefaultClassRefPkgName = "noDefaultClassRef";
        final String testDefaultClassRef = "com.opensymphony.xwork2.ActionSupport";

    	ConfigurationProvider provider = buildConfigurationProvider(filename);
        provider.init(configuration);

        // setup our expectations
        PackageConfig expectedDefaultClassRefPackage = new PackageConfig.Builder(hasDefaultClassRefPkgName).defaultClassRef(testDefaultClassRef).build();

        PackageConfig expectedNoDefaultClassRefPackage = new PackageConfig.Builder(noDefaultClassRefPkgName).build();

        // test expectations
        assertEquals(2, configuration.getPackageConfigs().size());
        assertEquals(expectedDefaultClassRefPackage, configuration.getPackageConfig(hasDefaultClassRefPkgName));
        assertEquals(expectedNoDefaultClassRefPackage, configuration.getPackageConfig(noDefaultClassRefPkgName));
    }
