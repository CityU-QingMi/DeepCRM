	protected Cache<Object, Object> getOrCreateCache(String regionName, Properties properties, CacheDataDescription metadata) {
		checkStatus();
		final Cache<Object, Object> cache = cacheManager.getCache( regionName );
		if ( cache == null ) {
			try {
				return createCache( regionName, properties, metadata );
			}
			catch ( CacheException e ) {
				final Cache<Object, Object> existing = cacheManager.getCache( regionName );
				if ( existing != null ) {
					return existing;
				}
				throw e;
			}
		}
		return cache;
	}
