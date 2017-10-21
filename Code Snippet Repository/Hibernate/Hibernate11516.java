	@Test
	public void testBuildEntityRegionPersonPlusEntityOverridesWithoutCfg() {
		final String person = "com.acme.Person";
		Properties p = createProperties();
		// Third option, no cache defined for entity and overrides for generic entity data type and entity itself.
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.eviction.strategy", "LRU");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.lifespan", "60000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.max_idle", "30000");
		p.setProperty("hibernate.cache.infinispan.entity.cfg", "myentity-cache");
		p.setProperty("hibernate.cache.infinispan.entity.eviction.strategy", "FIFO");
		p.setProperty("hibernate.cache.infinispan.entity.eviction.max_entries", "10000");
		p.setProperty("hibernate.cache.infinispan.entity.expiration.wake_up_interval", "3000");
		TestInfinispanRegionFactory factory = createRegionFactory(p);
		try {
			factory.getCacheManager();
			assertFalse( isDefinedCache(factory, person ) );
			EntityRegionImpl region = (EntityRegionImpl) factory.buildEntityRegion( person, p, MUTABLE_NON_VERSIONED );
			assertTrue( isDefinedCache(factory, person ) );
			AdvancedCache cache = region.getCache();
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals(EvictionStrategy.LRU, cacheCfg.eviction().strategy());
			assertEquals(3000, cacheCfg.expiration().wakeUpInterval());
			assertEquals(10000, cacheCfg.eviction().maxEntries());
			assertEquals(60000, cacheCfg.expiration().lifespan());
			assertEquals(30000, cacheCfg.expiration().maxIdle());
		} finally {
			factory.stop();
		}
	}
