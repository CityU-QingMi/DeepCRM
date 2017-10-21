	@Test
	public void cacheWithCustomKeyGenerator() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		service.cacheWithCustomKeyGenerator(keyItem, "ignored");

		assertNull(cache.get(key));
	}
