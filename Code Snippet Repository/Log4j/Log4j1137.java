    private static ShutdownCallbackRegistry createShutdownCallbackRegistry() {
        try {
            final ShutdownCallbackRegistry registry = LoaderUtil.newCheckedInstanceOfProperty(
                ShutdownCallbackRegistry.SHUTDOWN_CALLBACK_REGISTRY, ShutdownCallbackRegistry.class
            );
            if (registry != null) {
                return registry;
            }
        } catch (final Exception e) {
            LOGGER.error("Unable to create custom ShutdownCallbackRegistry. Falling back to default.", e);
        }
        return new DefaultShutdownCallbackRegistry();
    }
