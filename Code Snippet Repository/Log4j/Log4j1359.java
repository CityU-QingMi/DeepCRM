    private SSLContext createSslContext(final boolean loadDefaultKeyManagerFactory, final boolean loadDefaultTrustManagerFactory)
            throws KeyStoreConfigurationException, TrustStoreConfigurationException {
        try {
            KeyManager[] kManagers = null;
            TrustManager[] tManagers = null;

            final SSLContext newSslContext = SSLContext.getInstance(this.protocol);
            if (!loadDefaultKeyManagerFactory) {
                final KeyManagerFactory kmFactory = loadKeyManagerFactory();
                kManagers = kmFactory.getKeyManagers();
            }
            if (!loadDefaultTrustManagerFactory) {
                final TrustManagerFactory tmFactory = loadTrustManagerFactory();
                tManagers = tmFactory.getTrustManagers();
            }

            newSslContext.init(kManagers, tManagers, null);
            return newSslContext;
        }
        catch (final NoSuchAlgorithmException e) {
            LOGGER.error("No Provider supports a TrustManagerFactorySpi implementation for the specified protocol", e);
            throw new TrustStoreConfigurationException(e);
        }
        catch (final KeyManagementException e) {
            LOGGER.error("Failed to initialize the SSLContext", e);
            throw new KeyStoreConfigurationException(e);
        }
    }
