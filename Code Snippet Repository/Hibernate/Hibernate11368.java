	@Override
	protected EmbeddedCacheManager createCacheManager(
			Properties properties,
			ServiceRegistry serviceRegistry) throws CacheException {
		final String name = ConfigurationHelper.getString( CACHE_MANAGER_RESOURCE_PROP, properties, null );
		if ( name == null ) {
			throw log.propertyCacheManagerResourceNotSet();
		}
		return locateCacheManager( name, JndiHelper.extractJndiProperties( properties ) );
	}
