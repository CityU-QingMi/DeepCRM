    public static LoggerContext initialize(final String name, final ClassLoader loader, final List<URI> configLocations,
            final Object externalContext) {
        try {
            final Log4jContextFactory factory = getFactory();
            return factory == null ?
                    null :
                    factory.getContext(FQCN, loader, externalContext, false, configLocations, name);
        } catch (final Exception ex) {
            LOGGER.error("There was a problem initializing the LoggerContext [{}] using configurations at [{}].", name,
                    configLocations, ex);
        }
        return null;
    }
