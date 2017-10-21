	@Test
	public void testEhCacheFactoryBeanWithBlockingCache() throws Exception {
		EhCacheManagerFactoryBean cacheManagerFb = new EhCacheManagerFactoryBean();
		cacheManagerFb.afterPropertiesSet();
		try {
			CacheManager cm = cacheManagerFb.getObject();
			EhCacheFactoryBean cacheFb = new EhCacheFactoryBean();
			cacheFb.setCacheManager(cm);
			cacheFb.setCacheName("myCache1");
			cacheFb.setBlocking(true);
			assertEquals(cacheFb.getObjectType(), BlockingCache.class);
			cacheFb.afterPropertiesSet();
			Ehcache myCache1 = cm.getEhcache("myCache1");
			assertTrue(myCache1 instanceof BlockingCache);
		}
		finally {
			cacheManagerFb.destroy();
		}
	}
