	@Test
	public void testBuildEntityCollectionRegionOverridesOnly() {
		final String address = "com.acme.Address";
		final String personAddressses = "com.acme.Person.addresses";
		AdvancedCache cache;
		Properties p = createProperties();
		p.setProperty("hibernate.cache.infinispan.entity.eviction.strategy", "LIRS");
		p.setProperty("hibernate.cache.infinispan.entity.eviction.max_entries", "30000");
		p.setProperty("hibernate.cache.infinispan.entity.expiration.wake_up_interval", "3000");
		p.setProperty("hibernate.cache.infinispan.collection.eviction.strategy", "LRU");
		p.setProperty("hibernate.cache.infinispan.collection.eviction.max_entries", "35000");
		p.setProperty("hibernate.cache.infinispan.collection.expiration.wake_up_interval", "3500");
		TestInfinispanRegionFactory factory = createRegionFactory(p);
		try {
			factory.getCacheManager();
			EntityRegionImpl region = (EntityRegionImpl) factory.buildEntityRegion(address, p, MUTABLE_NON_VERSIONED);
			assertNull(factory.getBaseConfiguration(address));
			cache = region.getCache();
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals(EvictionStrategy.LIRS, cacheCfg.eviction().strategy());
			assertEquals(3000, cacheCfg.expiration().wakeUpInterval());
			assertEquals(30000, cacheCfg.eviction().maxEntries());
			// Max idle value comes from base XML configuration
			assertEquals(100000, cacheCfg.expiration().maxIdle());
			CollectionRegionImpl collectionRegion = (CollectionRegionImpl)
					factory.buildCollectionRegion(personAddressses, p, MUTABLE_NON_VERSIONED);
			assertNull(factory.getBaseConfiguration(personAddressses));
			cache = collectionRegion.getCache();
			cacheCfg = cache.getCacheConfiguration();
			assertEquals(EvictionStrategy.LRU, cacheCfg.eviction().strategy());
			assertEquals(3500, cacheCfg.expiration().wakeUpInterval());
			assertEquals(35000, cacheCfg.eviction().maxEntries());
			assertEquals(100000, cacheCfg.expiration().maxIdle());
		} finally {
			factory.stop();
		}
	}
