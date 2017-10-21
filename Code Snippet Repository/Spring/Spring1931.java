	@Test
	public void earlyPut() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		assertNull(cache.get(key));

		service.earlyPut(keyItem, value);

		Cache.ValueWrapper result = cache.get(key);
		assertNotNull(result);
		assertEquals(value, result.get());
	}
