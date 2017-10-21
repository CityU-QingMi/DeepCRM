	@Test
	public void removeWithException() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		cache.put(key, value);

		try {
			service.removeWithException(keyItem, true);
			fail("Should have thrown an exception");
		}
		catch (UnsupportedOperationException e) {
			// This is what we expect
		}

		assertNull(cache.get(key));
	}
