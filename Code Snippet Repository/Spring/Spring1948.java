	@Test
	public void cacheCheckedException() {
		String keyItem = name.getMethodName();
		Cache cache = getCache(EXCEPTION_CACHE);

		Object key = createKey(keyItem);
		assertNull(cache.get(key));

		try {
			service.cacheWithCheckedException(keyItem, true);
			fail("Should have thrown an exception");
		}
		catch (IOException e) {
			// This is what we expect
		}

		Cache.ValueWrapper result = cache.get(key);
		assertNotNull(result);
		assertEquals(IOException.class, result.get().getClass());
	}
