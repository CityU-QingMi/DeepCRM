	public PutFromLoadValidator(AdvancedCache cache, InfinispanRegionFactory regionFactory, EmbeddedCacheManager cacheManager) {
		this.regionFactory = regionFactory;
		Configuration cacheConfiguration = cache.getCacheConfiguration();
		Configuration pendingPutsConfiguration = regionFactory.getPendingPutsCacheConfiguration();
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.read(pendingPutsConfiguration);
		configurationBuilder.dataContainer().keyEquivalence(cacheConfiguration.dataContainer().keyEquivalence());
		String pendingPutsName = cache.getName() + "-" + InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE;
		cacheManager.defineConfiguration(pendingPutsName, configurationBuilder.build());

		if (pendingPutsConfiguration.expiration() != null && pendingPutsConfiguration.expiration().maxIdle() > 0) {
			this.expirationPeriod = pendingPutsConfiguration.expiration().maxIdle();
		}
		else {
			throw log.pendingPutsMustHaveMaxIdle();
		}
		CacheMode cacheMode = cache.getCacheConfiguration().clustering().cacheMode();
		// Since we need to intercept both invalidations of entries that are in the cache and those
		// that are not, we need to use custom interceptor, not listeners (which fire only for present entries).
		NonTxPutFromLoadInterceptor nonTxPutFromLoadInterceptor = null;
		if (cacheMode.isClustered()) {
			if (!cacheMode.isInvalidation()) {
				throw new IllegalArgumentException("PutFromLoadValidator in clustered caches requires invalidation mode.");
			}
			addToCache(cache, this);
		}

		this.cache = cache;
		this.pendingPuts = cacheManager.getCache(pendingPutsName);
	}
