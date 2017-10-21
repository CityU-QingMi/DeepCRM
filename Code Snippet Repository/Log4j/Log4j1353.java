    @PluginFactory
    public static KeyStoreConfiguration createKeyStoreConfiguration(
            // @formatter:off
            @PluginAttribute("location") final String location,
            @PluginAttribute(value = "password", sensitive = true) final char[] password,
            @PluginAttribute("type") final String keyStoreType,
            @PluginAttribute("keyManagerFactoryAlgorithm") final String keyManagerFactoryAlgorithm) throws StoreConfigurationException {
            // @formatter:on
        return new KeyStoreConfiguration(location, password, keyStoreType,
                keyManagerFactoryAlgorithm);
    }
