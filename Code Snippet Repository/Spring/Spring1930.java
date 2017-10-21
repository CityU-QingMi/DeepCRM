	@Test
	public void putWithExceptionVetoPut() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		assertNull(cache.get(key));

		try {
			service.putWithException(keyItem, value, false);
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			// This is what we expect
		}
		assertNull(cache.get(key));
	}
