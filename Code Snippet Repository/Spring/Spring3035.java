	private void doTestCacheGetCallableNotInvokedWithHit(Object initialValue) {
		T cache = getCache();

		String key = createRandomKey();
		cache.put(key, initialValue);

		Object value = cache.get(key, () -> {
			throw new IllegalStateException("Should not have been invoked");
		});
		assertEquals(initialValue, value);
	}
