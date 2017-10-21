    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext,
            final Object externalContext, final URI configLocation, final String name) {
        try {
            return factory.getContext(FQCN, loader, externalContext, currentContext, configLocation, name);
        } catch (final IllegalStateException ex) {
            LOGGER.warn(ex.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, loader, externalContext, currentContext,
                    configLocation, name);
        }
    }
