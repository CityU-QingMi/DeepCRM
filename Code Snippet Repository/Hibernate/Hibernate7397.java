	@Override
	protected void configure(Configuration configuration) {
		super.configure( configuration );
		configuration.getProperties().put( Environment.CREATE_EMPTY_COMPOSITES_ENABLED, "true" );
		configuration.getProperties().put( Environment.USE_SECOND_LEVEL_CACHE, "true" );
		configuration.getProperties().put( Environment.DEFAULT_CACHE_CONCURRENCY_STRATEGY, AccessType.READ_WRITE.getExternalName() );
		configuration.getProperties().put( Environment.USE_QUERY_CACHE, "true" );
		configuration.getProperties().put( Environment.GENERATE_STATISTICS, "true" );
		configuration.getProperties().put( Environment.CACHE_REGION_PREFIX, "" );
		configuration.getProperties().put( "javax.persistence.sharedCache.mode", SharedCacheMode.ALL );
	}
