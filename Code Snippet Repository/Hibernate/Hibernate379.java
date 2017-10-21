    protected void registerConnectionProvider(String tenantIdentifier) {
        Properties properties = properties();
        properties.put( Environment.URL,
            tenantUrl(properties.getProperty( Environment.URL ), tenantIdentifier) );

        DriverManagerConnectionProviderImpl connectionProvider =
            new DriverManagerConnectionProviderImpl();
        connectionProvider.configure( properties );
        connectionProviderMap.put( tenantIdentifier, connectionProvider );
    }
