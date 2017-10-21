    private void init() {
        registerConnectionProvider( FRONT_END_TENANT );
        registerConnectionProvider( BACK_END_TENANT );

        Map<String, Object> settings = new HashMap<>(  );

        settings.put( AvailableSettings.MULTI_TENANT, multiTenancyStrategy() );
        settings.put( AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER,
            new ConfigurableMultiTenantConnectionProvider( connectionProviderMap ) );

        sessionFactory = sessionFactory(settings);
    }
