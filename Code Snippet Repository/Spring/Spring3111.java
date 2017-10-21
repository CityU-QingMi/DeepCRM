	@Test
	public void runtimeResolution() {
		Cache defaultCache = this.cacheManager.getCache("default");
		Cache primaryCache = this.cacheManager.getCache("primary");

		Object key = new Object();
		assertCacheMiss(key, defaultCache, primaryCache);
		Object value = this.simpleService.getWithRuntimeCacheResolution(key, "default");
		assertCacheHit(key, value, defaultCache);
		assertCacheMiss(key, primaryCache);

		Object key2 = new Object();
		assertCacheMiss(key2, defaultCache, primaryCache);
		Object value2 = this.simpleService.getWithRuntimeCacheResolution(key2, "primary");
		assertCacheHit(key2, value2, primaryCache);
		assertCacheMiss(key2, defaultCache);
	}
