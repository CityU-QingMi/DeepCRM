	public QueryResultsRegionImpl(AdvancedCache cache, String name, TransactionManager transactionManager, InfinispanRegionFactory factory) {
		super( cache, name, transactionManager, null, factory, null );
		// If Infinispan is using INVALIDATION for query cache, we don't want to propagate changes.
		// We use the Timestamps cache to manage invalidation
		final boolean localOnly = Caches.isInvalidationCache( cache );

		this.evictCache = localOnly ? Caches.localCache( cache ) : cache;

		this.putCache = localOnly ?
				Caches.failSilentWriteCache( cache, Flag.CACHE_MODE_LOCAL ) :
				Caches.failSilentWriteCache( cache );

		this.getCache = Caches.failSilentReadCache( cache );

		TransactionConfiguration transactionConfiguration = putCache.getCacheConfiguration().transaction();
		boolean transactional = transactionConfiguration.transactionMode() != TransactionMode.NON_TRANSACTIONAL;
		this.putCacheRequiresTransaction = transactional && !transactionConfiguration.autoCommit();
		// Since we execute the query update explicitly form transaction synchronization, the putCache does not need
		// to be transactional anymore (it had to be in the past to prevent revealing uncommitted changes).
		if (transactional) {
			log.useNonTransactionalQueryCache();
		}

	}
