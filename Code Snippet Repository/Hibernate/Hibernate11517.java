	@Test
	public void testBuildImmutableEntityRegion() {
		AdvancedCache cache;
		Properties p = new Properties();
		TestInfinispanRegionFactory factory = createRegionFactory(p);
		try {
			factory.getCacheManager();
			EntityRegionImpl region = (EntityRegionImpl) factory.buildEntityRegion("com.acme.Address", p, IMMUTABLE_NON_VERSIONED);
			assertNull( factory.getBaseConfiguration( "com.acme.Address" ) );
			cache = region.getCache();
			Configuration cacheCfg = cache.getCacheConfiguration();
			assertEquals("Immutable entity should get non-transactional cache", TransactionMode.NON_TRANSACTIONAL, cacheCfg.transaction().transactionMode());
		} finally {
			factory.stop();
		}
	}
