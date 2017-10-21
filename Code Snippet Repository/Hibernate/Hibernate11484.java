	private void supportedAccessTypeTest() throws Exception {
		StandardServiceRegistryBuilder ssrb = createStandardServiceRegistryBuilder();
		final StandardServiceRegistry registry = ssrb.build();
		try {
			InfinispanRegionFactory regionFactory = CacheTestUtil.startRegionFactory(
					registry,
					getCacheTestSupport()
			);
			supportedAccessTypeTest( regionFactory, CacheTestUtil.toProperties( ssrb.getSettings() ) );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
