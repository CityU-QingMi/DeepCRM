	@Test
	public void cacheLoaderUseLoadingCache() {
		CaffeineCacheManager cm = new CaffeineCacheManager("c1");
		cm.setCacheLoader(new CacheLoader<Object, Object>() {
			@Override
			public Object load(Object key) throws Exception {
				if ("ping".equals(key)) {
					return "pong";
				}
				throw new IllegalArgumentException("I only know ping");
			}
		});
		Cache cache1 = cm.getCache("c1");
		Cache.ValueWrapper value = cache1.get("ping");
		assertNotNull(value);
		assertEquals("pong", value.get());

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("I only know ping");
		assertNull(cache1.get("foo"));
	}
