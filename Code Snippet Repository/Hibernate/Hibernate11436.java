	public BaseRegion(AdvancedCache cache, String name, TransactionManager transactionManager, InfinispanRegionFactory factory) {
		this.cache = cache;
		this.name = name;
		this.tm = transactionManager;
		this.factory = factory;
		this.localAndSkipLoadCache = cache.withFlags(
				Flag.CACHE_MODE_LOCAL, Flag.ZERO_LOCK_ACQUISITION_TIMEOUT,
				Flag.SKIP_CACHE_LOAD
		);
	}
