    public static AsyncQueueFullPolicy create() {
        final String router = PropertiesUtil.getProperties().getStringProperty(PROPERTY_NAME_ASYNC_EVENT_ROUTER);
        if (router == null || PROPERTY_VALUE_DEFAULT_ASYNC_EVENT_ROUTER.equals(router)
                || DefaultAsyncQueueFullPolicy.class.getSimpleName().equals(router)
                || DefaultAsyncQueueFullPolicy.class.getName().equals(router)) {
            return new DefaultAsyncQueueFullPolicy();
        }
        if (PROPERTY_VALUE_DISCARDING_ASYNC_EVENT_ROUTER.equals(router)
                || DiscardingAsyncQueueFullPolicy.class.getSimpleName().equals(router)
                || DiscardingAsyncQueueFullPolicy.class.getName().equals(router)) {
            return createDiscardingAsyncQueueFullPolicy();
        }
        return createCustomRouter(router);
    }
