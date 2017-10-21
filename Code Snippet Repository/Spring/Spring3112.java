	@Test
	public void namedResolution() {
		Cache cache = this.cacheManager.getCache("secondary");

		Object key = new Object();
		assertCacheMiss(key, cache);

		Object value = this.simpleService.getWithNamedCacheResolution(key);
		assertCacheHit(key, value, cache);
	}
