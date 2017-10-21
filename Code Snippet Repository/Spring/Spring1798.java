	@Override
	protected Cache getMissingCache(String name) {
		net.sf.ehcache.CacheManager cacheManager = getCacheManager();
		Assert.state(cacheManager != null, "No CacheManager set");

		// Check the EhCache cache again (in case the cache was added at runtime)
		Ehcache ehcache = cacheManager.getEhcache(name);
		if (ehcache != null) {
			return new EhCacheCache(ehcache);
		}
		return null;
	}
