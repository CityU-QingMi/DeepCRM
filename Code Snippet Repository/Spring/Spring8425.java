	@Test
	public void maxCacheSizeThree() {
		DefaultContextCache cache = new DefaultContextCache(3);
		assertEquals(0, cache.size());
		assertEquals(3, cache.getMaxSize());

		cache.put(fooConfig, fooContext);
		assertCacheContents(cache, "Foo");

		cache.put(fooConfig, fooContext);
		assertCacheContents(cache, "Foo");

		cache.put(barConfig, barContext);
		assertCacheContents(cache, "Foo", "Bar");

		cache.put(bazConfig, bazContext);
		assertCacheContents(cache, "Foo", "Bar", "Baz");

		cache.put(abcConfig, abcContext);
		assertCacheContents(cache, "Bar", "Baz", "Abc");
	}
