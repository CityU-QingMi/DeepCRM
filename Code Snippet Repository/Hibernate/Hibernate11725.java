	@SuppressWarnings("")
	public static Map buildBaselineSettings(
			String regionPrefix,
			Class regionFactory,
			boolean use2ndLevel,
			boolean useQueries,
			Class<? extends JtaPlatform> jtaPlatform) {
		Map settings = new HashMap();

		settings.put( AvailableSettings.GENERATE_STATISTICS, "true" );
		settings.put( AvailableSettings.USE_STRUCTURED_CACHE, "true" );
		if (jtaPlatform == null) {
			settings.put(Environment.TRANSACTION_COORDINATOR_STRATEGY, JdbcResourceLocalTransactionCoordinatorBuilderImpl.class.getName());
		} else {
			settings.put(Environment.TRANSACTION_COORDINATOR_STRATEGY, JtaTransactionCoordinatorBuilderImpl.class.getName());
			settings.put(AvailableSettings.JTA_PLATFORM, jtaPlatform);
		}
		settings.put( AvailableSettings.CACHE_REGION_FACTORY, regionFactory.getName() );
		settings.put( AvailableSettings.CACHE_REGION_PREFIX, regionPrefix );
		settings.put( AvailableSettings.USE_SECOND_LEVEL_CACHE, String.valueOf( use2ndLevel ) );
		settings.put( AvailableSettings.USE_QUERY_CACHE, String.valueOf( useQueries ) );

		return settings;
	}
