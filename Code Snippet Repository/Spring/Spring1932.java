	@Test
	public void earlyPutWithException() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		assertNull(cache.get(key));

		try {
			service.earlyPutWithException(keyItem, value, true);
			fail("Should have thrown an exception");
		}
		catch (UnsupportedOperationException e) {
			// This is what we expect
		}

		Cache.ValueWrapper result = cache.get(key);
		assertNotNull(result);
		assertEquals(value, result.get());
	}
