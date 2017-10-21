    public void createAdvertiser(final String advertiserString, final ConfigurationSource configSource) {
        byte[] buffer = null;
        try {
            if (configSource != null) {
                final InputStream is = configSource.getInputStream();
                if (is != null) {
                    buffer = toByteArray(is);
                }
            }
        } catch (final IOException ioe) {
            LOGGER.warn("Unable to read configuration source " + configSource.toString());
        }
        super.createAdvertiser(advertiserString, configSource, buffer, contentType);
    }
