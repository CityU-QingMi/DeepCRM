	@Override
	@SuppressWarnings("")
	protected void addSettings(Map settings) {
		super.addSettings( settings );

		settings.put( Environment.USE_SECOND_LEVEL_CACHE, "true" );
		settings.put( Environment.GENERATE_STATISTICS, "true" );
		settings.put( Environment.USE_QUERY_CACHE, String.valueOf( getUseQueryCache() ) );
		settings.put( Environment.CACHE_REGION_FACTORY, getRegionFactoryClass().getName() );
		settings.put( Environment.CACHE_KEYS_FACTORY, SimpleCacheKeysFactory.SHORT_NAME );
		settings.put( TestInfinispanRegionFactory.TRANSACTIONAL, useTransactionalCache );
		settings.put( TestInfinispanRegionFactory.CACHE_MODE, cacheMode);

		if ( jtaPlatformClass != null ) {
			settings.put( AvailableSettings.JTA_PLATFORM, jtaPlatformClass.getName() );
		}
		settings.put( Environment.TRANSACTION_COORDINATOR_STRATEGY, transactionCoordinatorBuilderClass.getName() );
		if ( connectionProviderClass != null) {
			settings.put(Environment.CONNECTION_PROVIDER, connectionProviderClass.getName());
		}
	}
