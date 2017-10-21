	@Test
	public void removeWithExceptionVetoRemove() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(keyItem);
		Object value = new Object();
		cache.put(key, value);

		try {
			service.removeWithException(keyItem, false);
			fail("Should have thrown an exception");
		}
		catch (NullPointerException e) {
			// This is what we expect
		}
		Cache.ValueWrapper wrapper = cache.get(key);
		assertNotNull(wrapper);
		assertEquals(value, wrapper.get());
	}
