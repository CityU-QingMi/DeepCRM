    @Override
    public String lookup(final LogEvent event, final String key) {
        if (key == null) {
            return null;
        }
        final String[] keys = key.split(":");
        final int keyLen = keys.length;
        if (keyLen != 2) {
            LOGGER.warn(LOOKUP, "Bad ResourceBundle key format [{}]. Expected format is BundleName:KeyName.", key);
            return null;
        }
        final String bundleName = keys[0];
        final String bundleKey = keys[1];
        try {
            // The ResourceBundle class caches bundles, no need to cache here.
            return ResourceBundle.getBundle(bundleName).getString(bundleKey);
        } catch (final MissingResourceException e) {
            LOGGER.warn(LOOKUP, "Error looking up ResourceBundle [{}].", bundleName, e);
            return null;
        }
    }
