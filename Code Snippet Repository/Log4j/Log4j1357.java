    private SSLContext createSslContextWithTrustStoreFailure() {
        SSLContext context;

        try {
            context = createSslContextWithDefaultTrustManagerFactory();
            LOGGER.debug("Creating SSLContext with default truststore");
        }
        catch (final KeyStoreConfigurationException e) {
            context = createDefaultSslContext();
            LOGGER.debug("Creating SSLContext with default configuration");
        }
        return context;
    }
