	@Override
	public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) throws CacheException {
		final EntityPersister persister = getPersister();
		if ( persister.hasCache() ) {
			final EntityRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			final Object ck = cache.generateCacheKey(
					getId(),
					persister,
					session.getFactory(),
					session.getTenantIdentifier()
					
			);

			if ( success &&
					cacheEntry != null &&
					!persister.isCacheInvalidationRequired() &&
					session.getCacheMode().isPutEnabled() ) {
				final boolean put = cacheAfterUpdate( cache, ck );

				if ( put && getSession().getFactory().getStatistics().isStatisticsEnabled() ) {
					getSession().getFactory().getStatistics().secondLevelCachePut( cache.getRegion().getName() );
				}
			}
			else {
				cache.unlockItem(session, ck, lock );
			}
		}
		postCommitUpdate( success );
	}
