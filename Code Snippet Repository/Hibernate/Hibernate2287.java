	private boolean isCached(Serializable collectionKey, CollectionPersister persister) {
		SharedSessionContractImplementor session = context.getSession();
		if ( session.getCacheMode().isGetEnabled() && persister.hasCache() ) {
			CollectionRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			Object cacheKey = cache.generateCacheKey(
					collectionKey,
					persister,
					session.getFactory(),
					session.getTenantIdentifier()
			);
			return CacheHelper.fromSharedCache( session, cacheKey, cache ) != null;
		}
		return false;
	}
