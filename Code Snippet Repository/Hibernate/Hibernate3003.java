	@Override
	public void evictCollection(String role, Serializable ownerIdentifier) {
		CollectionPersister p = sessionFactory.getMetamodel().collectionPersister( role );
		if ( p.hasCache() ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf(
						"Evicting second-level cache: %s",
						MessageHelper.collectionInfoString( p, ownerIdentifier, sessionFactory )
				);
			}
			CollectionRegionAccessStrategy cache = p.getCacheAccessStrategy();
			Object key = cache.generateCacheKey( ownerIdentifier, p, sessionFactory, null ); // have to assume non tenancy
			cache.evict( key );
		}
	}
