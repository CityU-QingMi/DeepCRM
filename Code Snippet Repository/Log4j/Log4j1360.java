    private TrustManagerFactory loadTrustManagerFactory() throws TrustStoreConfigurationException {
        if (trustStoreConfig == null) {
            throw new TrustStoreConfigurationException(new Exception("The trustStoreConfiguration is null"));
        }

        try {
            return trustStoreConfig.initTrustManagerFactory();
        }
        catch (final NoSuchAlgorithmException e) {
            LOGGER.error("The specified algorithm is not available from the specified provider", e);
            throw new TrustStoreConfigurationException(e);
        } catch (final KeyStoreException e) {
            LOGGER.error("Failed to initialize the TrustManagerFactory", e);
            throw new TrustStoreConfigurationException(e);
        }
    }
