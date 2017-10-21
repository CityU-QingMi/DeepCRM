    @Deprecated
    public static KeyStoreConfiguration createKeyStoreConfiguration(
            // @formatter:off
            final String location,
            final String password,
            final String keyStoreType,
            final String keyManagerFactoryAlgorithm) throws StoreConfigurationException {
            // @formatter:on
        return new KeyStoreConfiguration(location, password == null ? null : password.toCharArray(), keyStoreType,
                keyManagerFactoryAlgorithm);
    }
