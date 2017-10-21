	@Test
	public void cacheNull() {
		Cache cache = getCache(DEFAULT_CACHE);

		String keyItem = name.getMethodName();
		assertNull(cache.get(keyItem));

		Object first = service.cacheNull(keyItem);
		Object second = service.cacheNull(keyItem);
		assertSame(first, second);

		Cache.ValueWrapper wrapper = cache.get(keyItem);
		assertNotNull(wrapper);
		assertSame(first, wrapper.get());
		assertNull("Cached value should be null", wrapper.get());
	}
