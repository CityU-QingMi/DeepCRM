	@Test
	public void cacheWithCustomCacheResolver() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		service.cacheWithCustomCacheResolver(keyItem);

		assertNull(cache.get(key)); // Cache in mock cache
	}
