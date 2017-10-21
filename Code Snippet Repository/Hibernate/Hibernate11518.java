	@Test(expected = CacheException.class)
	public void testTimestampValidation() {
		final String timestamps = "org.hibernate.cache.spi.UpdateTimestampsCache";
		Properties p = createProperties();
      InputStream configStream = FileLookupFactory.newInstance().lookupFile(InfinispanRegionFactory.DEF_INFINISPAN_CONFIG_RESOURCE, getClass().getClassLoader());
      ConfigurationBuilderHolder cbh = new ParserRegistry().parse(configStream);
      DefaultCacheManager manager = new DefaultCacheManager(cbh, true);
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.clustering().cacheMode(CacheMode.INVALIDATION_SYNC);
		manager.defineConfiguration( DEF_TIMESTAMPS_RESOURCE, builder.build() );
		try {
			InfinispanRegionFactory factory = createRegionFactory( manager, p, null );
			factory.start( CacheTestUtil.sfOptionsForStart(), p );
			TimestampsRegionImpl region = (TimestampsRegionImpl) factory.buildTimestampsRegion( timestamps, p );
			fail( "Should have failed saying that invalidation is not allowed for timestamp caches." );
		} finally {
			TestingUtil.killCacheManagers( manager );
		}
	}
