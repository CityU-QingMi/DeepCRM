	public BaseTransactionalDataRegion(
			AdvancedCache cache, String name, TransactionManager transactionManager,
			CacheDataDescription metadata, InfinispanRegionFactory factory, CacheKeysFactory cacheKeysFactory) {
		super( cache, name, transactionManager, factory);
		this.metadata = metadata;
		this.cacheKeysFactory = cacheKeysFactory;

		Configuration configuration = cache.getCacheConfiguration();
		requiresTransaction = configuration.transaction().transactionMode().isTransactional()
				&& !configuration.transaction().autoCommit();
		tombstoneExpiration = factory.getPendingPutsCacheConfiguration().expiration().maxIdle();
		if (!isRegionAccessStrategyEnabled()) {
			strategy = Strategy.NONE;
		}
	}
