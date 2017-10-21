    protected ConfigurationProvider buildConfigurationProvider(final String filename) {
        configuration = new MockConfiguration();
        ((MockConfiguration)configuration).selfRegister();
        container = configuration.getContainer();

        XmlConfigurationProvider prov = new XmlConfigurationProvider(filename, true);
        container.inject(prov);
        prov.init(configuration);
        prov.loadPackages();
        return prov;
    }
