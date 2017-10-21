	@Override
	protected void addSettings(Map settings) {
		TestingJtaBootstrap.prepare( settings );
		//settings.put( AvailableSettings.TRANSACTION_STRATEGY, CMTTransactionFactory.class.getName() );
		settings.put( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, JtaTransactionCoordinatorBuilderImpl.class.getName() );
		settings.put( AvailableSettings.AUTO_CLOSE_SESSION, "true" );
		settings.put( AvailableSettings.FLUSH_BEFORE_COMPLETION, "true" );
		settings.put( AvailableSettings.RELEASE_CONNECTIONS, ConnectionReleaseMode.AFTER_STATEMENT.toString() );
		settings.put( AvailableSettings.GENERATE_STATISTICS, "true" );
		settings.put( AvailableSettings.USE_QUERY_CACHE, "true" );
		settings.put( AvailableSettings.CACHE_REGION_PREFIX, "" );
		settings.put( AvailableSettings.DEFAULT_ENTITY_MODE, EntityMode.MAP.toString() );
	}
