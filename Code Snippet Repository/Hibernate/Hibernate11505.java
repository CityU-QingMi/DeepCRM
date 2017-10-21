	@Test
	public void testBuildTimestampsRegionWithNoneEvictionOverride() {
		final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
		final String timestampsNoEviction = "timestamps-no-eviction";
		Properties p = createProperties();
		p.setProperty("hibernate.cache.infinispan.timestamps.cfg", timestampsNoEviction);
		p.setProperty("hibernate.cache.infinispan.timestamps.eviction.strategy", "NONE");
		p.setProperty("hibernate.cache.infinispan.timestamps.eviction.max_entries", "0");
		p.setProperty("hibernate.cache.infinispan.timestamps.expiration.wake_up_interval", "3000");
		InfinispanRegionFactory factory = createRegionFactory(p);
		try {
			TimestampsRegionImpl region = (TimestampsRegionImpl) factory.buildTimestampsRegion( timestamps, p );
			assertTrue( isDefinedCache(factory, timestamps) );
			assertEquals(3000, region.getCache().getCacheConfiguration().expiration().wakeUpInterval());
		} finally {
			factory.stop();
		}
	}
