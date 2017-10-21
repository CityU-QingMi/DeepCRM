	@Test
	public void testCacheRemove() throws Exception {
		T cache = getCache();

		String key = createRandomKey();
		Object value = "george";

		assertNull(cache.get(key));
		cache.put(key, value);
	}
