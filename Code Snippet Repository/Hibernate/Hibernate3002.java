	@Override
	public boolean containsCollection(String role, Serializable ownerIdentifier) {
		CollectionPersister p = sessionFactory.getMetamodel().collectionPersister( role );
		if ( p.hasCache() ) {
			CollectionRegionAccessStrategy cache = p.getCacheAccessStrategy();
			Object key = cache.generateCacheKey( ownerIdentifier, p, sessionFactory, null ); // have to assume non tenancy
			return cache.getRegion().contains( key );
		}
		else {
			return false;
		}
	}
