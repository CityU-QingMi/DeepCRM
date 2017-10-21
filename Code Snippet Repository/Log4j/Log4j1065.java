    public YamlConfigurationFactory() {
        for (final String dependency : dependencies) {
            if (!Loader.isClassAvailable(dependency)) {
                LOGGER.debug("Missing dependencies for Yaml support, ConfigurationFactory {} is inactive", getClass().getName());
                isActive = false;
                return;
            }
        }
        isActive = true;
    }
