	@Test
	public void testBuildQueryRegion() {
		final String query = "org.hibernate.cache.internal.StandardQueryCache";
		Properties p = createProperties();
		InfinispanRegionFactory factory = createRegionFactory(p);
		try {
			assertTrue(isDefinedCache(factory, "local-query"));
			QueryResultsRegionImpl region = (QueryResultsRegionImpl) factory.buildQueryResultsRegion(query, p);
			AdvancedCache cache = region.getCache();
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals( CacheMode.LOCAL, cacheCfg.clustering().cacheMode() );
			assertFalse( cacheCfg.jmxStatistics().enabled() );
		} finally {
			factory.stop();
		}
	}
