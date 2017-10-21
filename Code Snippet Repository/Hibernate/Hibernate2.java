    @Override
    public R initiateService(
            Map configurationValues,
            ServiceRegistryImplementor registry) {

        final boolean enabled = extractBoolean(
                configurationValues,
                ENABLE_PUBLISHING_SETTING
        );
        if ( enabled ) {
            return new EventPublishingServiceImpl();
        }
        else {
            return DisabledEventPublishingServiceImpl.INSTANCE;
        }
    }
