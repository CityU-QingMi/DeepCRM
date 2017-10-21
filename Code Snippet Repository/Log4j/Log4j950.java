    public static LoggerContext initialize(final String name, final ClassLoader loader, final URI configLocation,
                                           final Object externalContext) {

        try {
            final Log4jContextFactory factory = getFactory();
            return factory == null ? null :
                    factory.getContext(FQCN, loader, externalContext, false, configLocation, name);
        } catch (final Exception ex) {
            LOGGER.error("There was a problem initializing the LoggerContext [{}] using configuration at [{}].",
                    name, configLocation, ex);
        }
        return null;
    }
