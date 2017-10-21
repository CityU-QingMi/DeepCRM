	@Test
	public void testPutIfAbsentNullValue() throws Exception {
		CaffeineCache cache = getCache();

		Object key = new Object();
		Object value = null;

		assertNull(cache.get(key));
		assertNull(cache.putIfAbsent(key, value));
		assertEquals(value, cache.get(key).get());
		Cache.ValueWrapper wrapper = cache.putIfAbsent(key, "anotherValue");
		assertNotNull(wrapper); // A value is set but is 'null'
		assertEquals(null, wrapper.get());
		assertEquals(value, cache.get(key).get()); // not changed
	}
