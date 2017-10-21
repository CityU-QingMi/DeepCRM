	@Test
	public void ensureEvictedContextsAreClosed() {
		DefaultContextCache cache = new DefaultContextCache(2);

		cache.put(fooConfig, fooContext);
		cache.put(barConfig, barContext);
		assertCacheContents(cache, "Foo", "Bar");

		cache.put(bazConfig, bazContext);
		assertCacheContents(cache, "Bar", "Baz");
		verify(fooContext, times(1)).close();

		cache.put(abcConfig, abcContext);
		assertCacheContents(cache, "Baz", "Abc");
		verify(barContext, times(1)).close();

		verify(abcContext, never()).close();
		verify(bazContext, never()).close();
	}
