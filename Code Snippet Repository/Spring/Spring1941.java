	@Test
	public void removeAll() {
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(name.getMethodName());
		cache.put(key, new Object());

		service.removeAll();

		assertTrue(isEmpty(cache));
	}
