	@Test
	public void cacheExceptionVetoed() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(EXCEPTION_CACHE);

		Object key = createKey(keyItem);
		assertNull(cache.get(key));

		try {
			service.cacheWithException(keyItem, false);
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			// This is what we expect
		}
		assertNull(cache.get(key));
	}
