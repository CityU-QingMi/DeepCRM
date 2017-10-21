	@Test
	public void customCacheManager() {
		Cache cache = this.anotherCacheManager.getCache("default");

		Object key = new Object();
		assertCacheMiss(key, cache);

		Object value = this.simpleService.getWithCustomCacheManager(key);
		assertCacheHit(key, value, cache);
	}
