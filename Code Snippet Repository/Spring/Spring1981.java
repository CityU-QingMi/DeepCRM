	@Test
	public void getTransactionalOnNewCache() {
		String cacheName = name.getMethodName();
		T cacheManager = getCacheManager(true);
		assertFalse(cacheManager.getCacheNames().contains(cacheName));
		addNativeCache(cacheName);
		try {
			assertThat(cacheManager.getCache(cacheName),
					is(instanceOf(TransactionAwareCacheDecorator.class)));
			assertTrue(cacheManager.getCacheNames().contains(cacheName));
		}
		finally {
			removeNativeCache(cacheName);
		}
	}
