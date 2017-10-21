	@Test
	public void earlyRemoveAllWithExceptionVetoRemove() {
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(name.getMethodName());
		cache.put(key, new Object());

		try {
			service.earlyRemoveAllWithException(false);
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			// This is what we expect
		}
		// This will be remove anyway as the earlyRemove has removed the cache before
		assertTrue(isEmpty(cache));
	}
