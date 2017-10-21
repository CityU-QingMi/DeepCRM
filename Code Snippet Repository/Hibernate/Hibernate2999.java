	@Override
	public void evictEntity(String entityName, Serializable identifier) {
		EntityPersister p = sessionFactory.getMetamodel().entityPersister( entityName );
		if ( p.hasCache() ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf(
						"Evicting second-level cache: %s",
						MessageHelper.infoString( p, identifier, sessionFactory )
				);
			}
			EntityRegionAccessStrategy cache = p.getCacheAccessStrategy();
			Object key = cache.generateCacheKey( identifier, p, sessionFactory, null ); // have to assume non tenancy
			cache.evict( key );
		}
	}
