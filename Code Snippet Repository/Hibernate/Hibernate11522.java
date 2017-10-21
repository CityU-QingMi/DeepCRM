	@Test(expected = CacheException.class)
	public void testBuildTimestampsRegionWithFifoEvictionOverride() {
		final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
		final String myTimestampsCache = "mytimestamps-cache";
		Properties p = createProperties();
		p.setProperty(TIMESTAMPS_CACHE_RESOURCE_PROP, myTimestampsCache);
		p.setProperty("hibernate.cache.infinispan.timestamps.eviction.strategy", "FIFO");
		p.setProperty("hibernate.cache.infinispan.timestamps.eviction.max_entries", "10000");
		p.setProperty("hibernate.cache.infinispan.timestamps.expiration.wake_up_interval", "3000");
		InfinispanRegionFactory factory = null;
		try {
			factory = createRegionFactory(p);
			factory.buildTimestampsRegion(timestamps, p);
		} finally {
			if (factory != null) factory.stop();
		}
	}
