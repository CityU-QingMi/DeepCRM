    @Override
    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext,
                                    final URI configLocation) {

        final LoggerContext lc = ContextAnchor.THREAD_CONTEXT.get();
        if (lc != null) {
            return lc;
        }

        String loggingContextName = null;

        try (final JndiManager jndiManager = JndiManager.getDefaultManager()) {
            loggingContextName = jndiManager.lookup(Constants.JNDI_CONTEXT_NAME);
        } catch (final NamingException ne) {
            LOGGER.error("Unable to lookup {}", Constants.JNDI_CONTEXT_NAME, ne);
        }

        return loggingContextName == null ? CONTEXT : locateContext(loggingContextName, null, configLocation);
    }
