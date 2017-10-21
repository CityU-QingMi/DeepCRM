	public void testCacheManagerFromConfigFile() throws Exception {
		EhCacheManagerFactoryBean cacheManagerFb = new EhCacheManagerFactoryBean();
		cacheManagerFb.setConfigLocation(new ClassPathResource("testEhcache.xml", getClass()));
		cacheManagerFb.setCacheManagerName("myCacheManager");
		cacheManagerFb.afterPropertiesSet();
		try {
			CacheManager cm = cacheManagerFb.getObject();
			assertTrue("Correct number of caches loaded", cm.getCacheNames().length == 1);
			Cache myCache1 = cm.getCache("myCache1");
			assertFalse("myCache1 is not eternal", myCache1.getCacheConfiguration().isEternal());
			assertTrue("myCache1.maxElements == 300", myCache1.getCacheConfiguration().getMaxEntriesLocalHeap() == 300);
		}
		finally {
			cacheManagerFb.destroy();
		}
	}
