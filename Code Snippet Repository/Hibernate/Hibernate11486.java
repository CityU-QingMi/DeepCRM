	@Test
	public void testGetCacheDataDescription() throws Exception {
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
			CacheDataDescription cdd = region.getCacheDataDescription();
			assertNotNull( cdd );
			CacheDataDescription expected = getCacheDataDescription();
			assertEquals( expected.isMutable(), cdd.isMutable() );
			assertEquals( expected.isVersioned(), cdd.isVersioned() );
			assertEquals( expected.getVersionComparator(), cdd.getVersionComparator() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
