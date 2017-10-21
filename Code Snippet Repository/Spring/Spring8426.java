	@Test
	public void ensureLruOrderingIsUpdated() {
		DefaultContextCache cache = new DefaultContextCache(3);

		// Note: when a new entry is added it is considered the MRU entry and inserted at the tail.
		cache.put(fooConfig, fooContext);
		cache.put(barConfig, barContext);
		cache.put(bazConfig, bazContext);
		assertCacheContents(cache, "Foo", "Bar", "Baz");

		// Note: the MRU entry is moved to the tail when accessed.
		cache.get(fooConfig);
		assertCacheContents(cache, "Bar", "Baz", "Foo");

		cache.get(barConfig);
		assertCacheContents(cache, "Baz", "Foo", "Bar");

		cache.get(bazConfig);
		assertCacheContents(cache, "Foo", "Bar", "Baz");

		cache.get(barConfig);
		assertCacheContents(cache, "Foo", "Baz", "Bar");
	}
