    private SSLContext createSslContextWithKeyStoreFailure() {
        SSLContext context;

        try {
            context = createSslContextWithDefaultKeyManagerFactory();
            LOGGER.debug("Creating SSLContext with default keystore");
        }
        catch (final TrustStoreConfigurationException e) {
            context = createDefaultSslContext();
            LOGGER.debug("Creating SSLContext with default configuration");
        }
        return context;
    }
