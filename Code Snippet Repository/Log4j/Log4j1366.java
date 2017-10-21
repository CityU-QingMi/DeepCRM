    @Deprecated
    public static TrustStoreConfiguration createKeyStoreConfiguration(
            // @formatter:off
            final String location,
            final String password,
            final String keyStoreType,
            final String trustManagerFactoryAlgorithm) throws StoreConfigurationException {
            // @formatter:on
        return new TrustStoreConfiguration(location, password, keyStoreType, trustManagerFactoryAlgorithm);
    }
