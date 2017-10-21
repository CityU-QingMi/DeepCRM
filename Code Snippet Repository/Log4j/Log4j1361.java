    private KeyManagerFactory loadKeyManagerFactory() throws KeyStoreConfigurationException {
        if (keyStoreConfig == null) {
            throw new KeyStoreConfigurationException(new Exception("The keyStoreConfiguration is null"));
        }

        try {
            return keyStoreConfig.initKeyManagerFactory();
        }
        catch (final NoSuchAlgorithmException e) {
            LOGGER.error("The specified algorithm is not available from the specified provider", e);
            throw new KeyStoreConfigurationException(e);
        } catch (final KeyStoreException e) {
            LOGGER.error("Failed to initialize the TrustManagerFactory", e);
            throw new KeyStoreConfigurationException(e);
        } catch (final UnrecoverableKeyException e) {
            LOGGER.error("The key cannot be recovered (e.g. the given password is wrong)", e);
            throw new KeyStoreConfigurationException(e);
        }
    }
