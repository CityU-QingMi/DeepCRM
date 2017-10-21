	@Test
	public void noCustomization() {
		Cache cache = this.cacheManager.getCache("default");

		Object key = new Object();
		assertCacheMiss(key, cache);

		Object value = this.simpleService.getSimple(key);
		assertCacheHit(key, value, cache);
	}
