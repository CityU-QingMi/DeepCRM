    public static LoggerContext initialize(final ClassLoader loader,
                                           final ConfigurationSource source,
                                           final Object externalContext)
    {

        try {
            final Log4jContextFactory factory = getFactory();
            return factory == null ? null :
                    factory.getContext(FQCN, loader, externalContext, false, source);
        } catch (final Exception ex) {
            LOGGER.error("There was a problem obtaining a LoggerContext using the configuration source [{}]", source, ex);
        }
        return null;
    }
