	@Test
	public void testDefaultPendingPutsCache() {
		Properties p = createProperties();
		InfinispanRegionFactory factory = createRegionFactory(p);
		try {
			Configuration ppConfig = factory.getCacheManager().getCacheConfiguration(DEF_PENDING_PUTS_RESOURCE);

			assertTrue(ppConfig.isTemplate());
			assertFalse(ppConfig.clustering().cacheMode().isClustered());
			assertTrue(ppConfig.simpleCache());
			assertEquals(TransactionMode.NON_TRANSACTIONAL, ppConfig.transaction().transactionMode());
			assertEquals(60000, ppConfig.expiration().maxIdle());
			assertFalse(ppConfig.jmxStatistics().enabled());
			assertFalse(ppConfig.jmxStatistics().available());
		} finally {
			factory.stop();
		}
	}
