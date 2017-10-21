    @Override
    public Object visit(final Configuration configuration, final Node node, final LogEvent event,
                        final StringBuilder log) {
        if (this.conversionType.isInstance(configuration)) {
            log.append("Configuration");
            if (configuration.getName() != null) {
                log.append('(').append(configuration.getName()).append(')');
            }
            return configuration;
        }
        LOGGER.warn("Variable annotated with @PluginConfiguration is not compatible with type {}.",
            configuration.getClass());
        return null;
    }
