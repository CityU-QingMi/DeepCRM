	@Test
	public void testEhCacheFactoryBeanWithUpdatingSelfPopulatingCache() throws Exception {
		EhCacheManagerFactoryBean cacheManagerFb = new EhCacheManagerFactoryBean();
		cacheManagerFb.afterPropertiesSet();
		try {
			CacheManager cm = cacheManagerFb.getObject();
			EhCacheFactoryBean cacheFb = new EhCacheFactoryBean();
			cacheFb.setCacheManager(cm);
			cacheFb.setCacheName("myCache1");
			cacheFb.setCacheEntryFactory(new UpdatingCacheEntryFactory() {
				@Override
				public Object createEntry(Object key) throws Exception {
					return key;
				}
				@Override
				public void updateEntryValue(Object key, Object value) throws Exception {
				}
			});
			assertEquals(cacheFb.getObjectType(), UpdatingSelfPopulatingCache.class);
			cacheFb.afterPropertiesSet();
			Ehcache myCache1 = cm.getEhcache("myCache1");
			assertTrue(myCache1 instanceof UpdatingSelfPopulatingCache);
			assertEquals("myKey1", myCache1.get("myKey1").getObjectValue());
		}
		finally {
			cacheManagerFb.destroy();
		}
	}
