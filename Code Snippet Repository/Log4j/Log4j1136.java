    private static ContextSelector createContextSelector() {
        try {
            final ContextSelector selector = LoaderUtil.newCheckedInstanceOfProperty(Constants.LOG4J_CONTEXT_SELECTOR,
                ContextSelector.class);
            if (selector != null) {
                return selector;
            }
        } catch (final Exception e) {
            LOGGER.error("Unable to create custom ContextSelector. Falling back to default.", e);
        }
        return new ClassLoaderContextSelector();
    }
