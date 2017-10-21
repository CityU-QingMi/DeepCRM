    @PluginFactory
    public static TrustStoreConfiguration createKeyStoreConfiguration(
            // @formatter:off
            @PluginAttribute("location") final String location,
            @PluginAttribute(value = "password", sensitive = true) final char[] password,
            @PluginAttribute("type") final String keyStoreType,
            @PluginAttribute("trustManagerFactoryAlgorithm") final String trustManagerFactoryAlgorithm) throws StoreConfigurationException {
            // @formatter:on
        return new TrustStoreConfiguration(location, password, keyStoreType, trustManagerFactoryAlgorithm);
    }
