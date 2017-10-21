	private void assertSharedConfig(CacheOperation actual, String keyGenerator, String cacheManager,
			String cacheResolver, String... cacheNames) {

		assertEquals("Wrong key manager",  keyGenerator, actual.getKeyGenerator());
		assertEquals("Wrong cache manager", cacheManager, actual.getCacheManager());
		assertEquals("Wrong cache resolver", cacheResolver, actual.getCacheResolver());
		assertEquals("Wrong number of cache names", cacheNames.length, actual.getCacheNames().size());
		Arrays.stream(cacheNames).forEach(cacheName ->
				assertTrue("Cache '" + cacheName + "' not found in " + actual.getCacheNames(),
						actual.getCacheNames().contains(cacheName)));
	}
