	@Override
	protected void addSettings(Map settings) {
		settings.put( AvailableSettings.USE_QUERY_CACHE, "true" );
		settings.put(
				AvailableSettings.CACHE_REGION_PREFIX,
				"criteriaquerytest"
		);
		settings.put( AvailableSettings.USE_SECOND_LEVEL_CACHE, "true" );
		settings.put( AvailableSettings.GENERATE_STATISTICS, "true" );
		settings.put(
				AvailableSettings.CONNECTION_PROVIDER,
				connectionProvider
		);
	}
