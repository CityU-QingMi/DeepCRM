	@Test
	public void maxCacheSizeOne() {
		DefaultContextCache cache = new DefaultContextCache(1);
		assertEquals(0, cache.size());
		assertEquals(1, cache.getMaxSize());

		cache.put(fooConfig, fooContext);
		assertCacheContents(cache, "Foo");

		cache.put(fooConfig, fooContext);
		assertCacheContents(cache, "Foo");

		cache.put(barConfig, barContext);
		assertCacheContents(cache, "Bar");

		cache.put(fooConfig, fooContext);
		assertCacheContents(cache, "Foo");
	}
