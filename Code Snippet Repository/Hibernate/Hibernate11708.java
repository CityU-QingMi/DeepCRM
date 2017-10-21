	public void testClearTimestampsRegionInIsolated() throws Exception {
		StandardServiceRegistryBuilder ssrb = createStandardServiceRegistryBuilder();
		final StandardServiceRegistry registry = ssrb.build();
		final StandardServiceRegistry registry2 = ssrb.build();

		try {
			final Properties properties = CacheTestUtil.toProperties( ssrb.getSettings() );

			InfinispanRegionFactory regionFactory = CacheTestUtil.startRegionFactory(
					  registry,
					  getCacheTestSupport()
			);

			InfinispanRegionFactory regionFactory2 = CacheTestUtil.startRegionFactory(
					  registry2,
					  getCacheTestSupport()
			);

			TimestampsRegionImpl region = (TimestampsRegionImpl) regionFactory.buildTimestampsRegion(
					  getStandardRegionName(REGION_PREFIX),
					  properties
			);
			TimestampsRegionImpl region2 = (TimestampsRegionImpl) regionFactory2.buildTimestampsRegion(
					  getStandardRegionName(REGION_PREFIX),
					  properties
			);

			Account acct = new Account();
			acct.setAccountHolder(new AccountHolder());
			region.getCache().withFlags(Flag.FORCE_SYNCHRONOUS).put(acct, "boo");
		}
		finally {
			StandardServiceRegistryBuilder.destroy( registry );
			StandardServiceRegistryBuilder.destroy( registry2 );
		}
	}
