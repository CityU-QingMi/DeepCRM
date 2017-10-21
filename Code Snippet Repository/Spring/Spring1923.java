	@Test
	public void testEhCacheFactoryBeanWithSelfPopulatingCache() throws Exception {
		EhCacheManagerFactoryBean cacheManagerFb = new EhCacheManagerFactoryBean();
		cacheManagerFb.afterPropertiesSet();
		try {
			CacheManager cm = cacheManagerFb.getObject();
			EhCacheFactoryBean cacheFb = new EhCacheFactoryBean();
			cacheFb.setCacheManager(cm);
			cacheFb.setCacheName("myCache1");
			cacheFb.setCacheEntryFactory(new CacheEntryFactory() {
				@Override
				public Object createEntry(Object key) throws Exception {
					return key;
				}
			});
			assertEquals(cacheFb.getObjectType(), SelfPopulatingCache.class);
			cacheFb.afterPropertiesSet();
			Ehcache myCache1 = cm.getEhcache("myCache1");
			assertTrue(myCache1 instanceof SelfPopulatingCache);
			assertEquals("myKey1", myCache1.get("myKey1").getObjectValue());
		}
		finally {
			cacheManagerFb.destroy();
		}
	}
