    private SSLContext createSslContext() {
        SSLContext context = null;

        try {
            context = createSslContextBasedOnConfiguration();
            LOGGER.debug("Creating SSLContext with the given parameters");
        }
        catch (final TrustStoreConfigurationException e) {
            context = createSslContextWithTrustStoreFailure();
        }
        catch (final KeyStoreConfigurationException e) {
            context = createSslContextWithKeyStoreFailure();
        }
        return context;
    }
