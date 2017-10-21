        private Configuration getConfiguration(final LoggerContext loggerContext, final String configLocationStr) {
            ConfigurationSource source = null;
            try {
                source = ConfigurationSource.fromUri(NetUtils.toURI(configLocationStr));
            } catch (final Exception ex) {
                // Ignore the error and try as a String.
                LOGGER.catching(Level.DEBUG, ex);
            }
            if (source == null) {
                final ClassLoader loader = LoaderUtil.getThreadContextClassLoader();
                source = getInputFromString(configLocationStr, loader);
            }
            if (source != null) {
                for (final ConfigurationFactory factory : getFactories()) {
                    final String[] types = factory.getSupportedTypes();
                    if (types != null) {
                        for (final String type : types) {
                            if (type.equals(ALL_TYPES) || configLocationStr.endsWith(type)) {
                                final Configuration config = factory.getConfiguration(loggerContext, source);
                                if (config != null) {
                                    return config;
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
