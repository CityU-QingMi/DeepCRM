	@Override
	public void evictQueryRegion(String regionName) {
		if ( regionName == null ) {
			throw new NullPointerException(
					"Region-name cannot be null (use Cache#evictDefaultQueryRegion to evict the default query cache)"
			);
		}
		if ( sessionFactory.getSessionFactoryOptions().isQueryCacheEnabled() ) {
			QueryCache namedQueryCache = queryCaches.get( regionName );
			// TODO : cleanup entries in queryCaches + allCacheRegions ?
			if ( namedQueryCache != null ) {
				if ( LOG.isDebugEnabled() ) {
					LOG.debugf( "Evicting query cache, region: %s", regionName );
				}
				namedQueryCache.clear();
			}
		}
	}
