	@Test
	public void testBuildDefaultTimestampsRegion() {
		final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
		Properties p = createProperties();
		InfinispanRegionFactory factory = createRegionFactory(p);
		try {
			assertTrue(isDefinedCache(factory, DEF_TIMESTAMPS_RESOURCE));
			TimestampsRegionImpl region = (TimestampsRegionImpl) factory.buildTimestampsRegion(timestamps, p);
			AdvancedCache cache = region.getCache();
			assertEquals(timestamps, cache.getName());
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals( EvictionStrategy.NONE, cacheCfg.eviction().strategy() );
			assertEquals( CacheMode.REPL_ASYNC, cacheCfg.clustering().cacheMode() );
			assertFalse( cacheCfg.jmxStatistics().enabled() );
		} finally {
			factory.stop();
		}
	}
