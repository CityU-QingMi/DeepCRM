    public JsonConfigurationFactory() {
        for (final String dependency : dependencies) {
            if (!Loader.isClassAvailable(dependency)) {
                LOGGER.debug("Missing dependencies for Json support, ConfigurationFactory {} is inactive", getClass().getName());
                isActive = false;
                return;
            }
        }
        isActive = true;
    }
