    protected void setUp() throws Exception {
        super.setUp();
        req = new MockHttpServletRequest();
        req.setContextPath("/myapp");
        req.setMethod("GET");

        mapper = new RestActionMapper();

        config = new DefaultConfiguration();
        PackageConfig pkg = new PackageConfig.Builder("myns").namespace("/animals").build();
        PackageConfig pkg2 = new PackageConfig.Builder("my").namespace("/my").build();
        config.addPackageConfig("mvns", pkg);
        config.addPackageConfig("my", pkg2);
        configManager = new ConfigurationManager(Container.DEFAULT_NAME) {
            public Configuration getConfiguration() {
                return config;
            }
        };
    }
