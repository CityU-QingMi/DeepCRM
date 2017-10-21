	@Test
	public void testCachePutIfAbsent() throws Exception {
		T cache = getCache();

		String key = createRandomKey();
		Object value = "initialValue";

		assertNull(cache.get(key));
		assertNull(cache.putIfAbsent(key, value));
		assertEquals(value, cache.get(key).get());
		assertEquals("initialValue", cache.putIfAbsent(key, "anotherValue").get());
		assertEquals(value, cache.get(key).get()); // not changed
	}
