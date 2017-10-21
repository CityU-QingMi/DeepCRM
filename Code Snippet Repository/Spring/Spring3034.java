	private void doTestCacheGetCallable(Object returnValue) {
		T cache = getCache();

		String key = createRandomKey();

		assertNull(cache.get(key));
		Object value = cache.get(key, () -> returnValue );
		assertEquals(returnValue, value);
		assertEquals(value, cache.get(key).get());
	}
