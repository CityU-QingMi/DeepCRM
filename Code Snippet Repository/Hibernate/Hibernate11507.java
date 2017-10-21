	@Test
	public void testBuildQueryRegionWithCustomRegionName() {
		final String queryRegionName = "myquery";
		Properties p = createProperties();
		p.setProperty("hibernate.cache.infinispan.myquery.cfg", "timestamps-none-eviction");
		p.setProperty("hibernate.cache.infinispan.myquery.eviction.strategy", "LIRS");
		p.setProperty("hibernate.cache.infinispan.myquery.expiration.wake_up_interval", "2222");
		p.setProperty("hibernate.cache.infinispan.myquery.eviction.max_entries", "11111");
		TestInfinispanRegionFactory factory = createRegionFactory(p);
		try {
			assertTrue(isDefinedCache(factory, "local-query"));
			QueryResultsRegionImpl region = (QueryResultsRegionImpl) factory.buildQueryResultsRegion(queryRegionName, p);
			assertNotNull(factory.getBaseConfiguration(queryRegionName));
			assertTrue(isDefinedCache(factory, queryRegionName));
			AdvancedCache cache = region.getCache();
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals(EvictionStrategy.LIRS, cacheCfg.eviction().strategy());
			assertEquals(2222, cacheCfg.expiration().wakeUpInterval());
			assertEquals( 11111, cacheCfg.eviction().maxEntries() );
		} finally {
			factory.stop();
		}
	}
