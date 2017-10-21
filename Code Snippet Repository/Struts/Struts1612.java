    @Override
    protected void setUp() throws Exception {
        super.setUp();

        XmlConfigurationProvider provider1 = new XmlConfigurationProvider("xwork-default.xml");
        container.inject(provider1);
        XmlConfigurationProvider provider2 = new XmlConfigurationProvider("xwork-test-validation.xml");
        container.inject(provider2);
        loadConfigurationProviders(provider1, provider2);
    }
