    @Override
    public T build(final boolean initialize) {
        T configuration;
        try {
            if (source == null) {
                source = ConfigurationSource.NULL_SOURCE;
            }
            final Constructor<T> constructor = clazz.getConstructor(LoggerContext.class, ConfigurationSource.class, Component.class);
            configuration = constructor.newInstance(loggerContext, source, root);
            configuration.setMonitorInterval(monitorInterval);
            configuration.getRootNode().getAttributes().putAll(root.getAttributes());
            if (name != null) {
                configuration.setName(name);
            }
            if (level != null) {
                configuration.getStatusConfiguration().withStatus(level);
            }
            if (verbosity != null) {
                configuration.getStatusConfiguration().withVerbosity(verbosity);
            }
            if (destination != null) {
                configuration.getStatusConfiguration().withDestination(destination);
            }
            if (packages != null) {
                configuration.setPluginPackages(packages);
            }
            if (shutdownFlag != null) {
                configuration.setShutdownHook(shutdownFlag);
            }
            if (shutdownTimeoutMillis > 0) {
                configuration.setShutdownTimeoutMillis(shutdownTimeoutMillis);
            }
            if (advertiser != null) {
                configuration.createAdvertiser(advertiser, source);
            }
        } catch (final Exception ex) {
            throw new IllegalArgumentException("Invalid Configuration class specified", ex);
        }
        configuration.getStatusConfiguration().initialize();
        if (initialize) {
            configuration.initialize();
        }
        return configuration;
    }
