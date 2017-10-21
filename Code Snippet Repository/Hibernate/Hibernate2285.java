	private boolean isCached(EntityKey entityKey, EntityPersister persister) {
		final SharedSessionContractImplementor session = context.getSession();
		if ( context.getSession().getCacheMode().isGetEnabled() && persister.hasCache() ) {
			final EntityRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			final Object key = cache.generateCacheKey(
					entityKey.getIdentifier(),
					persister,
					session.getFactory(),
					session.getTenantIdentifier()
			);
			return CacheHelper.fromSharedCache( session, key, cache ) != null;
		}
		return false;
	}
