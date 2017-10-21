	protected final void evict() throws CacheException {
		if ( persister.hasCache() ) {
			final CollectionRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			final Object ck = cache.generateCacheKey(
					key, 
					persister,
					session.getFactory(),
					session.getTenantIdentifier()
			);
			cache.remove( session, ck);
		}
	}
