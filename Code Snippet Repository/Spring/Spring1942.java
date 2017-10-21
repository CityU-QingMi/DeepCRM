	@Test
	public void removeAllWithException() {
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(name.getMethodName());
		cache.put(key, new Object());

		try {
			service.removeAllWithException(true);
			fail("Should have thrown an exception");
		}
		catch (UnsupportedOperationException e) {
			// This is what we expect
		}

		assertTrue(isEmpty(cache));
	}
