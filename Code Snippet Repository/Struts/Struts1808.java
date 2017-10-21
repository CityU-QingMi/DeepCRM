    public void testGetMappingWithSlashedNameAtRoot() throws Exception {
        config = new DefaultConfiguration();
        PackageConfig pkg = new PackageConfig.Builder("myns")
            .namespace("/my/namespace").build();
        PackageConfig pkg2 = new PackageConfig.Builder("my").namespace("/my").build();
        PackageConfig pkg3 = new PackageConfig.Builder("root").namespace("/").build();
        config.addPackageConfig("mvns", pkg);
        config.addPackageConfig("my", pkg2);
        config.addPackageConfig("root", pkg3);
        configManager = new ConfigurationManager(Container.DEFAULT_NAME) {
            public Configuration getConfiguration() {
                return config;
            }
        };

        req.setupGetRequestURI("/foo/actionName.action");
        req.setupGetServletPath("/foo/actionName.action");
        req.setupGetAttribute(null);
        req.addExpectedGetAttributeName("javax.servlet.include.servlet_path");

        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setSlashesInActionNames("true");
        ActionMapping mapping = mapper.getMapping(req, configManager);

        assertEquals("/", mapping.getNamespace());
        assertEquals("foo/actionName", mapping.getName());
        assertNull(mapping.getMethod());
    }
