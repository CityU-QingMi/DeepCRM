    public void testInitForPackageProviders() {
        
        loadConfigurationProviders(new StubConfigurationProvider() {
            @Override
            public void register(ContainerBuilder builder,
                    LocatableProperties props) throws ConfigurationException {
                builder.factory(PackageProvider.class, "foo", MyPackageProvider.class);
            }
        });
        
        assertEquals(configuration, MyPackageProvider.getConfiguration());
    }
