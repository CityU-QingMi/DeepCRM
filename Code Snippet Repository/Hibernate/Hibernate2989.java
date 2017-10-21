	public CacheImpl(SessionFactoryImplementor sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.settings = sessionFactory.getSessionFactoryOptions();
		this.regionFactory = settings.getServiceRegistry().getService( RegionFactory.class );
		this.regionFactory.start( settings, sessionFactory.getProperties() );

		this.cacheRegionPrefix = StringHelper.isEmpty( sessionFactory.getSessionFactoryOptions().getCacheRegionPrefix() )
				? ""
				: sessionFactory.getSessionFactoryOptions().getCacheRegionPrefix() + ".";

		if ( settings.isQueryCacheEnabled() ) {
			final TimestampsRegion timestampsRegion = regionFactory.buildTimestampsRegion(
					qualifyRegionName( UpdateTimestampsCache.REGION_NAME ),
					sessionFactory.getProperties()
			);
			updateTimestampsCache = new UpdateTimestampsCache( sessionFactory, timestampsRegion );
			final QueryResultsRegion queryResultsRegion = regionFactory.buildQueryResultsRegion(
					StandardQueryCache.class.getName(),
					sessionFactory.getProperties()
			);
			defaultQueryCache = settings.getQueryCacheFactory().buildQueryCache( queryResultsRegion, this );
			queryCaches = new ConcurrentHashMap<>();
		}
		else {
			updateTimestampsCache = null;
			defaultQueryCache = null;
			queryCaches = null;
		}
	}
