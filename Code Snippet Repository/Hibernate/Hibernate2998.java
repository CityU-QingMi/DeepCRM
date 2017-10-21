	@Override
	public boolean containsEntity(String entityName, Serializable identifier) {
		EntityPersister p = sessionFactory.getMetamodel().entityPersister( entityName );
		if ( p.hasCache() ) {
			EntityRegionAccessStrategy cache = p.getCacheAccessStrategy();
			Object key = cache.generateCacheKey( identifier, p, sessionFactory, null ); // have to assume non tenancy
			return cache.getRegion().contains( key );
		}
		else {
			return false;
		}
	}
