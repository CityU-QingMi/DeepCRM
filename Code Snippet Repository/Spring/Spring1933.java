	@Test
	public void earlyPutWithExceptionVetoPut() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		assertNull(cache.get(key));

		try {
			service.earlyPutWithException(keyItem, value, false);
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			// This is what we expect
		}
		// This will be cached anyway as the earlyPut has updated the cache before
		Cache.ValueWrapper result = cache.get(key);
		assertNotNull(result);
		assertEquals(value, result.get());
	}
