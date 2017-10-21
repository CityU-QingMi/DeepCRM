	@Test
	public void testCustomPendingPutsCache() {
		Properties p = createProperties();
		p.setProperty(INFINISPAN_CONFIG_RESOURCE_PROP, "alternative-infinispan-configs.xml");
		InfinispanRegionFactory factory = createRegionFactory(p);
		try {
			Configuration ppConfig = factory.getCacheManager().getCacheConfiguration(DEF_PENDING_PUTS_RESOURCE);
			assertEquals(120000, ppConfig.expiration().maxIdle());
		} finally {
			factory.stop();
		}
	}
