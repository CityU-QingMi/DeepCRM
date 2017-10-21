        @Override
        public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
            if (source != null) {
                final String config = source.getLocation();
                for (final ConfigurationFactory factory : getFactories()) {
                    final String[] types = factory.getSupportedTypes();
                    if (types != null) {
                        for (final String type : types) {
                            if (type.equals(ALL_TYPES) || config != null && config.endsWith(type)) {
                                final Configuration c = factory.getConfiguration(loggerContext, source);
                                if (c != null) {
                                    LOGGER.debug("Loaded configuration from {}", source);
                                    return c;
                                }
                                LOGGER.error("Cannot determine the ConfigurationFactory to use for {}", config);
                                return null;
                            }
                        }
                    }
                }
            }
            LOGGER.error("Cannot process configuration, input source is null");
            return null;
        }
