    @Override
    protected KeyStore load() throws StoreConfigurationException {
        final String loadLocation = this.getLocation();
        LOGGER.debug("Loading keystore from location {}", loadLocation);
        try {
            if (loadLocation == null) {
                throw new IOException("The location is null");
            }
            try (final InputStream fin = openInputStream(loadLocation)) {
                final KeyStore ks = KeyStore.getInstance(this.keyStoreType);
                ks.load(fin, this.getPasswordAsCharArray());
                LOGGER.debug("KeyStore successfully loaded from location {}", loadLocation);
                return ks;
            }
        } catch (final CertificateException e) {
            LOGGER.error("No Provider supports a KeyStoreSpi implementation for the specified type {} for location {}", this.keyStoreType, loadLocation, e);
            throw new StoreConfigurationException(loadLocation, e);
        } catch (final NoSuchAlgorithmException e) {
            LOGGER.error("The algorithm used to check the integrity of the keystore cannot be found for location {}", loadLocation, e);
            throw new StoreConfigurationException(loadLocation, e);
        } catch (final KeyStoreException e) {
            LOGGER.error("KeyStoreException for location {}", loadLocation, e);
            throw new StoreConfigurationException(loadLocation, e);
        } catch (final FileNotFoundException e) {
            LOGGER.error("The keystore file {} is not found", loadLocation, e);
            throw new StoreConfigurationException(loadLocation, e);
        } catch (final IOException e) {
            LOGGER.error("Something is wrong with the format of the keystore or the given password for location", loadLocation, e);
            throw new StoreConfigurationException(loadLocation, e);
        }
    }
