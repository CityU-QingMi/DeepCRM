	@Override
	@SuppressWarnings({ "" })
	public RegionFactory initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final Properties p = new Properties();
		if (configurationValues != null) {
			p.putAll( configurationValues );
		}
		
		final boolean useSecondLevelCache = ConfigurationHelper.getBoolean(
				AvailableSettings.USE_SECOND_LEVEL_CACHE,
				configurationValues,
				true
		);
		final boolean useQueryCache = ConfigurationHelper.getBoolean(
				AvailableSettings.USE_QUERY_CACHE,
				configurationValues
		);

		RegionFactory regionFactory = NoCachingRegionFactory.INSTANCE;

		// The cache provider is needed when we either have second-level cache enabled
		// or query cache enabled.  Note that useSecondLevelCache is enabled by default
		if ( useSecondLevelCache || useQueryCache ) {
			final Object setting = configurationValues != null
					? configurationValues.get( AvailableSettings.CACHE_REGION_FACTORY )
					: null;
			regionFactory = registry.getService( StrategySelector.class ).resolveStrategy(
					RegionFactory.class,
					setting,
					NoCachingRegionFactory.INSTANCE,
					new StrategyCreatorRegionFactoryImpl( p )
			);
		}

		LOG.debugf( "Cache region factory : %s", regionFactory.getClass().getName() );

		return regionFactory;
	}
