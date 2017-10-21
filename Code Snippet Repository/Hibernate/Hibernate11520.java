	@Test
	public void testBuildDiffCacheNameTimestampsRegion() {
		final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
		final String unrecommendedTimestamps = "unrecommended-timestamps";
		Properties p = createProperties();
		p.setProperty( TIMESTAMPS_CACHE_RESOURCE_PROP, unrecommendedTimestamps);
		TestInfinispanRegionFactory factory = createRegionFactory(p, (f, m) -> {
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.clustering().stateTransfer().fetchInMemoryState(true);
			builder.clustering().cacheMode( CacheMode.REPL_SYNC );
			m.defineConfiguration(unrecommendedTimestamps, builder.build() );
		});
		try {
			assertEquals(unrecommendedTimestamps, factory.getBaseConfiguration(DataType.TIMESTAMPS));
			TimestampsRegionImpl region = (TimestampsRegionImpl) factory.buildTimestampsRegion(timestamps, p);
			AdvancedCache cache = region.getCache();
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals(EvictionStrategy.NONE, cacheCfg.eviction().strategy());
			assertEquals(CacheMode.REPL_SYNC, cacheCfg.clustering().cacheMode());
			assertFalse( cacheCfg.storeAsBinary().enabled() );
			assertFalse(cacheCfg.jmxStatistics().enabled());
		} finally {
			factory.stop();
		}
	}
