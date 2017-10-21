        private Configuration getConfiguration(final LoggerContext loggerContext, final boolean isTest, final String name) {
            final boolean named = Strings.isNotEmpty(name);
            final ClassLoader loader = LoaderUtil.getThreadContextClassLoader();
            for (final ConfigurationFactory factory : getFactories()) {
                String configName;
                final String prefix = isTest ? TEST_PREFIX : DEFAULT_PREFIX;
                final String [] types = factory.getSupportedTypes();
                if (types == null) {
                    continue;
                }

                for (final String suffix : types) {
                    if (suffix.equals(ALL_TYPES)) {
                        continue;
                    }
                    configName = named ? prefix + name + suffix : prefix + suffix;

                    final ConfigurationSource source = ConfigurationSource.fromResource(configName, loader);
                    if (source != null) {
                        if (!factory.isActive()) {
                            LOGGER.warn("Found configuration file {} for inactive ConfigurationFactory {}", configName, factory.getClass().getName());
                        }
                        return factory.getConfiguration(loggerContext, source);
                    }
                }
            }
            return null;
        }
