	@Test
	public void testBuildTimestampsRegionWithCacheNameOverride() {
		final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
		final String myTimestampsCache = "mytimestamps-cache";
		Properties p = createProperties();
		p.setProperty(TIMESTAMPS_CACHE_RESOURCE_PROP, myTimestampsCache);
		InfinispanRegionFactory factory = createRegionFactory(p, (f, m) -> {
			ClusteringConfigurationBuilder builder = new ConfigurationBuilder().clustering().cacheMode(CacheMode.LOCAL);
			m.defineConfiguration(myTimestampsCache, builder.build());
		});
		try {
			TimestampsRegionImpl region = (TimestampsRegionImpl) factory.buildTimestampsRegion(timestamps, p);
			assertTrue(isDefinedCache(factory, timestamps));
			// default timestamps cache is async replicated
			assertEquals(CacheMode.LOCAL, region.getCache().getCacheConfiguration().clustering().cacheMode());
		} finally {
			factory.stop();
		}
	}
