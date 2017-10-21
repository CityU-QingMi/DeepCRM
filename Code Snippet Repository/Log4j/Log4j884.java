    private static AsyncQueueFullPolicy createCustomRouter(final String router) {
        try {
            final Class<? extends AsyncQueueFullPolicy> cls = LoaderUtil.loadClass(router).asSubclass(AsyncQueueFullPolicy.class);
            LOGGER.debug("Creating custom AsyncQueueFullPolicy '{}'", router);
            return cls.newInstance();
        } catch (final Exception ex) {
            LOGGER.debug("Using DefaultAsyncQueueFullPolicy. Could not create custom AsyncQueueFullPolicy '{}': {}", router,
                    ex.toString());
            return new DefaultAsyncQueueFullPolicy();
        }
    }
