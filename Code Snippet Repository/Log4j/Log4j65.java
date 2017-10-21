    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext,
            final Object externalContext, final URI configLocation) {
        try {
            return factory.getContext(FQCN, loader, externalContext, currentContext, configLocation, null);
        } catch (final IllegalStateException ex) {
            LOGGER.warn(ex.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, loader, externalContext, currentContext,
                    configLocation, null);
        }
    }
