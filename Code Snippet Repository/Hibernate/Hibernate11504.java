	@Test
	public void testConfigurationProcessing() {
		final String person = "com.acme.Person";
		final String addresses = "com.acme.Person.addresses";
		Properties p = createProperties();
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.cfg", "person-cache");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.eviction.strategy", "LRU");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.eviction.max_entries", "5000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.wake_up_interval", "2000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.lifespan", "60000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.max_idle", "30000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.addresses.cfg", "person-addresses-cache");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.addresses.expiration.lifespan", "120000");
		p.setProperty("hibernate.cache.infinispan.com.acme.Person.addresses.expiration.max_idle", "60000");
		p.setProperty("hibernate.cache.infinispan.query.cfg", "my-query-cache");
		p.setProperty("hibernate.cache.infinispan.query.eviction.strategy", "LIRS");
		p.setProperty("hibernate.cache.infinispan.query.expiration.wake_up_interval", "3000");
		p.setProperty("hibernate.cache.infinispan.query.eviction.max_entries", "10000");

		TestInfinispanRegionFactory factory = createRegionFactory(p);

		try {
			assertEquals("person-cache", factory.getBaseConfiguration(person));
			Configuration personOverride = factory.getConfigurationOverride(person);
			assertEquals(EvictionStrategy.LRU, personOverride.eviction().strategy());
			assertEquals(5000, personOverride.eviction().maxEntries());
			assertEquals(2000, personOverride.expiration().wakeUpInterval());
			assertEquals(60000, personOverride.expiration().lifespan());
			assertEquals(30000, personOverride.expiration().maxIdle());

			assertEquals("person-addresses-cache", factory.getBaseConfiguration(addresses));
			Configuration addressesOverride = factory.getConfigurationOverride(addresses);
			assertEquals(120000, addressesOverride.expiration().lifespan());
			assertEquals(60000, addressesOverride.expiration().maxIdle());

			assertEquals("my-query-cache", factory.getBaseConfiguration(DataType.QUERY));
			Configuration queryOverride = factory.getConfigurationOverride(DataType.QUERY);
			assertEquals(EvictionStrategy.LIRS, queryOverride.eviction().strategy());
			assertEquals(10000, queryOverride.eviction().maxEntries());
			assertEquals(3000, queryOverride.expiration().wakeUpInterval());
		} finally {
			factory.stop();
		}
	}
