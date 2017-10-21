	@Test
	public void customCacheResolver() {
		Cache cache = this.cacheManager.getCache("primary");

		Object key = new Object();
		assertCacheMiss(key, cache);

		Object value = this.simpleService.getWithCustomCacheResolver(key);
		assertCacheHit(key, value, cache);
	}
