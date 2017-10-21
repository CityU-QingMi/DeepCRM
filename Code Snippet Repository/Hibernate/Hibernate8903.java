	@SuppressWarnings("")
	@Override
	protected void addSettings(Map settings) {
		super.addSettings( settings );

		settings.put( AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, "true" );
		settings.put( Environment.USE_SECOND_LEVEL_CACHE, "true" );
		settings.put( Environment.USE_QUERY_CACHE, "true" );
		settings.put( Environment.CACHE_PROVIDER_CONFIG, "true" );
	}
