    @Override
    public void setUp() throws Exception {
        super.setUp();

        sac = new StaticApplicationContext();
        loadConfigurationProviders(new StubConfigurationProvider() {

            @Override
            public void register(ContainerBuilder builder,
                    LocatableProperties props) throws ConfigurationException {
                builder.factory(ObjectFactory.class, SpringObjectFactory.class);
            }
            
        });

        objectFactory = (SpringObjectFactory) container.getInstance(ObjectFactory.class);
        objectFactory.setApplicationContext(sac);
        objectFactory.setAlwaysRespectAutowireStrategy(false);
    }
