    @Override
    public Configuration reconfigure() {
        LOGGER.debug("Reconfiguring composite configuration");
        final List<AbstractConfiguration> configs = new ArrayList<>();
        final ConfigurationFactory factory = ConfigurationFactory.getInstance();
        for (final AbstractConfiguration config : configurations) {
            final ConfigurationSource source = config.getConfigurationSource();
            final URI sourceURI = source.getURI();
            Configuration currentConfig;
            if (sourceURI == null) {
                LOGGER.warn("Unable to determine URI for configuration {}, changes to it will be ignored",
                        config.getName());
                currentConfig = factory.getConfiguration(getLoggerContext(), config.getName(), sourceURI);
                if (currentConfig == null) {
                    LOGGER.warn("Unable to reload configuration {}, changes to it will be ignored", config.getName());
                    currentConfig = config;
                }
            } else {
                currentConfig = config;
            }
            configs.add((AbstractConfiguration) currentConfig);

        }

        return new CompositeConfiguration(configs);
    }
