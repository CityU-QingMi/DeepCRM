	@Test
	public void testBlankCacheManager() throws Exception {
		EhCacheManagerFactoryBean cacheManagerFb = new EhCacheManagerFactoryBean();
		cacheManagerFb.setCacheManagerName("myCacheManager");
		assertEquals(CacheManager.class, cacheManagerFb.getObjectType());
		assertTrue("Singleton property", cacheManagerFb.isSingleton());
		cacheManagerFb.afterPropertiesSet();
		try {
			CacheManager cm = cacheManagerFb.getObject();
			assertTrue("Loaded CacheManager with no caches", cm.getCacheNames().length == 0);
			Cache myCache1 = cm.getCache("myCache1");
			assertTrue("No myCache1 defined", myCache1 == null);
		}
		finally {
			cacheManagerFb.destroy();
		}
	}
