	@Test
	public void testCachePut() throws Exception {
		T cache = getCache();

		String key = createRandomKey();
		Object value = "george";

		assertNull(cache.get(key));
		assertNull(cache.get(key, String.class));
		assertNull(cache.get(key, Object.class));

		cache.put(key, value);
		assertEquals(value, cache.get(key).get());
		assertEquals(value, cache.get(key, String.class));
		assertEquals(value, cache.get(key, Object.class));
		assertEquals(value, cache.get(key, (Class<?>) null));

		cache.put(key, null);
		assertNotNull(cache.get(key));
		assertNull(cache.get(key).get());
		assertNull(cache.get(key, String.class));
		assertNull(cache.get(key, Object.class));
	}
