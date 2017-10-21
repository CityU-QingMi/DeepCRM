	@Test
	public void earlyRemoveAll() {
		Cache cache = getCache(DEFAULT_CACHE);

		Object key = createKey(name.getMethodName());
		cache.put(key, new Object());

		service.earlyRemoveAll();

		assertTrue(isEmpty(cache));
	}
