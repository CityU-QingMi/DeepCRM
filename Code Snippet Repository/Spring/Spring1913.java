	@Test
	public void changeCacheLoaderRecreateCache() {
		CaffeineCacheManager cm = new CaffeineCacheManager("c1");
		Cache cache1 = cm.getCache("c1");

		CacheLoader<Object, Object> loader = mockCacheLoader();
		cm.setCacheLoader(loader);
		Cache cache1x = cm.getCache("c1");
		assertTrue(cache1x != cache1);

		cm.setCacheLoader(loader); // Set same instance
		Cache cache1xx = cm.getCache("c1");
		assertSame(cache1x, cache1xx);
	}
