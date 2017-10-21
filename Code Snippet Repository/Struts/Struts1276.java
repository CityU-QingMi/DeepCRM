    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loadConfigurationProviders(new ConfigurationProvider() {
            Configuration configuration;
            public void destroy() {
            }
            
            public void init(Configuration config) {
                this.configuration = config;
            }

            public void loadPackages() {
                PackageConfig packageConfig = new PackageConfig.Builder("package")
                        .addActionConfig("action", new ActionConfig.Builder("package", "action", SimpleFooAction.class.getName()).build())
                        .build();
                configuration.addPackageConfig("package", packageConfig);
            }

/**/
/**/
/**/
            public boolean needsReload() {
                return false;
            }

            public void register(ContainerBuilder builder, LocatableProperties props) throws ConfigurationException {
                builder.factory(ActionProxyFactory.class, DefaultActionProxyFactory.class);
                builder.factory(ObjectFactory.class);
                
            }
        });
    }
