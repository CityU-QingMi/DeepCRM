	@Test
	public void getOnNewCache() {
		T cacheManager = getCacheManager(false);
		String cacheName = name.getMethodName();
		addNativeCache(cacheName);
		assertFalse(cacheManager.getCacheNames().contains(cacheName));
		try {
			assertThat(cacheManager.getCache(cacheName), is(instanceOf(getCacheType())));
			assertTrue(cacheManager.getCacheNames().contains(cacheName));
		}
		finally {
			removeNativeCache(cacheName);
		}
	}
