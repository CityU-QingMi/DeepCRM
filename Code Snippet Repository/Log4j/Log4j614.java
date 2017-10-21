    private void reconfigure(final URI configURI) {
        final ClassLoader cl = ClassLoader.class.isInstance(externalContext) ? (ClassLoader) externalContext : null;
        LOGGER.debug("Reconfiguration started for context[name={}] at URI {} ({}) with optional ClassLoader: {}",
                contextName, configURI, this, cl);
        final Configuration instance = ConfigurationFactory.getInstance().getConfiguration(this, contextName, configURI, cl);
        if (instance == null) {
            LOGGER.error("Reconfiguration failed: No configuration found for '{}' at '{}' in '{}'", contextName, configURI, cl);
        } else {
            setConfiguration(instance);
/**/
/**/
/**/
/**/
            final String location = configuration == null ? "?" : String.valueOf(configuration.getConfigurationSource());
            LOGGER.debug("Reconfiguration complete for context[name={}] at URI {} ({}) with optional ClassLoader: {}",
                    contextName, location, this, cl);
        }
    }
