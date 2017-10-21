	@Test
	public void cacheException() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(EXCEPTION_CACHE);

		Object key = createKey(keyItem);
		assertNull(cache.get(key));

		try {
			service.cacheWithException(keyItem, true);
			fail("Should have thrown an exception");
		}
		catch (UnsupportedOperationException e) {
			// This is what we expect
		}

		Cache.ValueWrapper result = cache.get(key);
		assertNotNull(result);
		assertEquals(UnsupportedOperationException.class, result.get().getClass());
	}
