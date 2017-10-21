    public static LoggerContext initialize(final ClassLoader loader, final Configuration configuration, final Object externalContext) {
        try {
            final Log4jContextFactory factory = getFactory();
            return factory == null ? null :
                    factory.getContext(FQCN, loader, externalContext, false, configuration);
        } catch (final Exception ex) {
            LOGGER.error("There was a problem initializing the LoggerContext using configuration {}",
                    configuration.getName(), ex);
        }
        return null;
    }
