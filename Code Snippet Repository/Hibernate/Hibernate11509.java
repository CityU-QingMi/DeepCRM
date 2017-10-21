	@Test
	public void testDisableStatistics() {
		Properties p = createProperties();
		p.setProperty("hibernate.cache.infinispan.statistics", "false");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.lifespan", "60000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.max_idle", "30000");
		p.setProperty("hibernate.cache.infinispan.entity.cfg", "myentity-cache");
		p.setProperty("hibernate.cache.infinispan.entity.eviction.strategy", "FIFO");
		p.setProperty("hibernate.cache.infinispan.entity.expiration.wake_up_interval", "3000");
		p.setProperty("hibernate.cache.infinispan.entity.eviction.max_entries", "10000");
		InfinispanRegionFactory factory = createRegionFactory(p);
		try {
			EntityRegionImpl region = (EntityRegionImpl) factory.buildEntityRegion("com.acme.Address", p, MUTABLE_NON_VERSIONED);
			AdvancedCache cache = region.getCache();
			assertFalse( cache.getCacheConfiguration().jmxStatistics().enabled() );

			region = (EntityRegionImpl) factory.buildEntityRegion("com.acme.Person", p, MUTABLE_NON_VERSIONED);
			cache = region.getCache();
			assertFalse( cache.getCacheConfiguration().jmxStatistics().enabled() );

			final String query = "org.hibernate.cache.internal.StandardQueryCache";
			QueryResultsRegionImpl queryRegion = (QueryResultsRegionImpl) factory.buildQueryResultsRegion(query, p);
			cache = queryRegion.getCache();
			assertFalse( cache.getCacheConfiguration().jmxStatistics().enabled() );

			final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.clustering().stateTransfer().fetchInMemoryState(true);
			factory.getCacheManager().defineConfiguration( "timestamps", builder.build() );
			TimestampsRegionImpl timestampsRegion = (TimestampsRegionImpl)
					factory.buildTimestampsRegion(timestamps, p);
			cache = timestampsRegion.getCache();
			assertFalse( cache.getCacheConfiguration().jmxStatistics().enabled() );

			CollectionRegionImpl collectionRegion = (CollectionRegionImpl)
					factory.buildCollectionRegion("com.acme.Person.addresses", p, MUTABLE_NON_VERSIONED);
			cache = collectionRegion.getCache();
			assertFalse( cache.getCacheConfiguration().jmxStatistics().enabled() );
		} finally {
			factory.stop();
		}
	}
