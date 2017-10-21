	@Override
	protected EmbeddedCacheManager createCacheManager(ConfigurationBuilderHolder holder) {
		// If the cache manager has been provided by calling setCacheManager, don't create a new one
		EmbeddedCacheManager cacheManager = getCacheManager();
		if (cacheManager != null) {
			return cacheManager;
		}
		amendConfiguration(holder);
		cacheManager = new DefaultCacheManager(holder, true);
		if (timeService != null) {
			cacheManager.getGlobalComponentRegistry().registerComponent(timeService, TimeService.class);
			cacheManager.getGlobalComponentRegistry().rewire();
		}
		return cacheManager;
	}
