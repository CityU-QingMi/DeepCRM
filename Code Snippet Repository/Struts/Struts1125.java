    public void testInheritence() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-include-parent.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);

        provider.init(configuration);
        provider.loadPackages();

        // test expectations
        assertEquals(6, configuration.getPackageConfigs().size());


        PackageConfig defaultPackage = configuration.getPackageConfig("default");
        assertNotNull(defaultPackage);
        assertEquals("default", defaultPackage.getName());


        PackageConfig namespace1 = configuration.getPackageConfig("namespace1");
        assertNotNull(namespace1);
        assertEquals("namespace1", namespace1.getName());
        assertEquals(defaultPackage, namespace1.getParents().get(0));

        PackageConfig namespace2 = configuration.getPackageConfig("namespace2");
        assertNotNull(namespace2);
        assertEquals("namespace2", namespace2.getName());
        assertEquals(1, namespace2.getParents().size());
        assertEquals(namespace1, namespace2.getParents().get(0));


        PackageConfig namespace4 = configuration.getPackageConfig("namespace4");
        assertNotNull(namespace4);
        assertEquals("namespace4", namespace4.getName());
        assertEquals(1, namespace4.getParents().size());
        assertEquals(namespace1, namespace4.getParents().get(0));


        PackageConfig namespace5 = configuration.getPackageConfig("namespace5");
        assertNotNull(namespace5);
        assertEquals("namespace5", namespace5.getName());
        assertEquals(1, namespace5.getParents().size());
        assertEquals(namespace4, namespace5.getParents().get(0));

        configurationManager.addContainerProvider(provider);
        configurationManager.reload();

        RuntimeConfiguration runtimeConfiguration = configurationManager.getConfiguration().getRuntimeConfiguration();
        assertNotNull(runtimeConfiguration.getActionConfig("/namespace1", "action1"));
        assertNotNull(runtimeConfiguration.getActionConfig("/namespace2", "action2"));
        assertNotNull(runtimeConfiguration.getActionConfig("/namespace4", "action4"));
        assertNotNull(runtimeConfiguration.getActionConfig("/namespace5", "action5"));
    }
