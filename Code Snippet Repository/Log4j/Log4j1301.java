    @Override
    public String lookup(final LogEvent event, final String key) {
        if (configuration != null) {
            final ConfigurationSource configSrc = configuration.getConfigurationSource();
            final File file = configSrc.getFile();
            if (file != null) {
                switch (key) {
                    case KEY_CONFIG_LOCATION:
                        return file.getAbsolutePath();

                    case KEY_CONFIG_PARENT_LOCATION:
                        return file.getParentFile().getAbsolutePath();

                    default:
                        return null;
                }
            }

            final URL url = configSrc.getURL();
            if (url != null) {
                try {
                    switch (key) {
                        case KEY_CONFIG_LOCATION:
                            return asPath(url.toURI());

                        case KEY_CONFIG_PARENT_LOCATION:
                            return asPath(getParent(url.toURI()));

                        default:
                            return null;
                    }
                } catch (final URISyntaxException use) {
                    LOGGER.error(use);
                    return null;
                }
            }
        }

        return null;
    }
