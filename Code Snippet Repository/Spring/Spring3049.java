	@Test
	public void testNoOpCache() throws Exception {
		String name = createRandomKey();
		Cache cache = this.manager.getCache(name);
		assertEquals(name, cache.getName());
		Object key = new Object();
		cache.put(key, new Object());
		assertNull(cache.get(key));
		assertNull(cache.get(key, Object.class));
		assertSame(cache, cache.getNativeCache());
	}
