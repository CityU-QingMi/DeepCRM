	@Test
	public void earlyRemoveWithExceptionVetoRemove() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		cache.put(key, value);

		try {
			service.earlyRemoveWithException(keyItem, false);
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			// This is what we expect
		}
		// This will be remove anyway as the earlyRemove has removed the cache before
		assertNull(cache.get(key));
	}
