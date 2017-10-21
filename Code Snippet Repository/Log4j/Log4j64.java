    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext,
            final URI configLocation) {
        try {
            return factory.getContext(FQCN, loader, null, currentContext, configLocation, null);
        } catch (final IllegalStateException ex) {
            LOGGER.warn(ex.getMessage() + " Using SimpleLogger");
            return new SimpleLoggerContextFactory().getContext(FQCN, loader, null, currentContext, configLocation,
                    null);
        }
    }
