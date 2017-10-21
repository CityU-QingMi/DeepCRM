	@Test
	public void testIsTransactionAware() throws Exception {
		StandardServiceRegistryBuilder ssrb = CacheTestUtil.buildBaselineStandardServiceRegistryBuilder(
				"test",
				InfinispanRegionFactory.class,
				true,
				false,
				jtaPlatform
		);
		final StandardServiceRegistry registry = ssrb.build();
		try {
			Properties properties = CacheTestUtil.toProperties( ssrb.getSettings() );
			InfinispanRegionFactory regionFactory = CacheTestUtil.startRegionFactory(
					registry,
					getCacheTestSupport()
			);
			TransactionalDataRegion region = (TransactionalDataRegion) createRegion(
					regionFactory,
					"test/test",
					properties,
					getCacheDataDescription()
			);
			assertTrue( "Region is transaction-aware", region.isTransactionAware() );
			CacheTestUtil.stopRegionFactory( regionFactory, getCacheTestSupport() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
