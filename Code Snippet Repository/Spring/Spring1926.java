	@Before
	public void setupOnce() {
		cacheManagerMock = new CacheManagerMock();
		cacheManagerMock.addCache(CACHE_NAME);

		cacheManager = new JCacheCacheManager(cacheManagerMock.getCacheManager());
		cacheManager.setTransactionAware(false);
		cacheManager.afterPropertiesSet();

		transactionalCacheManager = new JCacheCacheManager(cacheManagerMock.getCacheManager());
		transactionalCacheManager.setTransactionAware(true);
		transactionalCacheManager.afterPropertiesSet();
	}
